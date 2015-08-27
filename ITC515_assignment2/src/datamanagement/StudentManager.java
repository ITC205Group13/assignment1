package datamanagement;

import org.jdom.*;
import java.util.List;

public class StudentManager {
    private static StudentManager self = null;
    private StudentMap studentMap;
    private java.util.HashMap<String, StudentMap> unitMap;
    
    public static StudentManager get() {
        if (self == null) 
        	self = new StudentManager(); return self; 
    }
    
    private StudentManager() {
    	studentMap = new StudentMap();
        unitMap = new java.util.HashMap<>();
    }
    
    public IStudent getStudent(Integer studentId) {
    	IStudent student = studentMap.get(studentId);
    	return student != null ? student : createStudent(studentId);
    }

    private Element getStudentElement(Integer studentId) {
        for (Element studentElement : (List<Element>)XMLManager.getXML().getDocument().getRootElement().getChild("studentTable")
        		.getChildren("student")) 
            if (studentId.toString().equals(studentElement.getAttributeValue("sid"))) 
            	return studentElement;
        return null;
    }
    
    private IStudent createStudent(Integer studentId) {
    	IStudent student;
        Element studentElement = getStudentElement(studentId);
        if (studentElement != null) {
            StudentUnitRecordList unitRecordList = StudentUnitRecordManager.instance().getRecordsByStudent(studentId);
            student = new Student(new Integer(studentElement.getAttributeValue("sid")),studentElement.getAttributeValue("fname"),
            		studentElement.getAttributeValue("lname"),unitRecordList);
            studentMap.put(student.getID(),student);
            return student;
        }
        throw new RuntimeException("DBMD: createStudent : student not in file");
    }
    
    private IStudent createStudentProxy(Integer studentId) {
        Element studentElement = getStudentElement(studentId);
        if (studentElement != null) {
        	return new StudentProxy(studentId, studentElement.getAttributeValue("fname"),studentElement.getAttributeValue("lname"));
        }
        throw new RuntimeException("DBMD: createStudent : student not in file");  
    }

    public StudentMap getStudentsByUnit(String unitCode) {
        StudentMap studentMap = unitMap.get(unitCode);
        if (studentMap != null) {
        	return studentMap;
        }
        studentMap = new StudentMap();
        IStudent student;
        StudentUnitRecordList studentUnitRecord = StudentUnitRecordManager.instance().getRecordsByUnit(unitCode);
        for (IStudentUnitRecord s : studentUnitRecord) {
        	student = createStudentProxy(new Integer(s.getStudentID()));
        	studentMap.put(student.getID(),student);
        }
        unitMap.put(unitCode,studentMap);
        return studentMap;
    }
}
