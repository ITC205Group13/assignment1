package  datamanagement;

import  java.util.List;
import  org.jdom.*;

public  class  StudentUnitRecordManager  {

	private  static  StudentUnitRecordManager  student  =  null;
	private  StudentUnitRecordMap  recordMap;
	private  java.util.HashMap<String,StudentUnitRecordList>  unitRecord;
	private java.util.HashMap<Integer,StudentUnitRecordList>  studentRecord;

	public  static  StudentUnitRecordManager  instance()  {

		if  (student  ==  null) 

			student  =  new  StudentUnitRecordManager(); 

		return  student;

	}

	private  StudentUnitRecordManager()  {

		recordMap  =  new  StudentUnitRecordMap();
		unitRecord  =  new  java.util.HashMap<>();
		studentRecord  =  new  java.util.HashMap<>();

	}

	public  IStudentUnitRecord  getStudentUnitRecord(Integer  studentId, String  unitCode)  {

		IStudentUnitRecord  ir  =  recordMap.get(studentId.toString()  +  unitCode);
		return  ir !=  null  ?  ir  :  createStudentUnitRecord(studentId,  unitCode);

	}

	private  IStudentUnitRecord  createStudentUnitRecord(Integer  unitId,  String  studentId )  {

		IStudentUnitRecord  unitRecord;

		for  (Element  el  :  (List<Element>)  XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record"))  {

			if  (unitId.toString().equals(el.getAttributeValue("sid"))  &&  studentId.equals(el.getAttributeValue("uid")))  {

				unitRecord  =  new  StudentUnitRecord(new  Integer(el.getAttributeValue("sid")),  
						el.getAttributeValue("uid"), 
						new Float(el.getAttributeValue("asg1")).floatValue(), 
						new  Float(el.getAttributeValue("asg2")).floatValue(), 
						new Float(el.getAttributeValue("exam")).floatValue());
				
				recordMap.put(unitRecord.getStudentID().toString()  +  unitRecord.getUnitCode(),  unitRecord);

				return  unitRecord;
			}
		}

		throw  new  RuntimeException("DBMD:  createStudent  :  student  unit  record  not  in  file");

	}


	public  StudentUnitRecordList  getRecordsByUnit(String  unitCode)  {

		StudentUnitRecordList  records  =  unitRecord.get(unitCode);

		if  (records  !=  null)  return records; 
		records  =  new  StudentUnitRecordList();

		for  (Element  el  :  (List<Element>)  XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record"))  {
			if (unitCode.equals(el.getAttributeValue("uid"))) records.add(new  StudentUnitRecordProxy(new  Integer(el.getAttributeValue("sid")),  el.getAttributeValue("uid")));
		}

		if  (records.size()  >  0) 

			unitRecord.put(unitCode,  records); // be careful - this could be empty
		return  records;
	}

	public  StudentUnitRecordList  getRecordsByStudent(Integer  studentId)  {

		StudentUnitRecordList  records = studentRecord.get(studentId);

		if  (records  !=  null) 

			return records;

		records  =  new  StudentUnitRecordList();

		for  (Element  el  :  (List<Element>)  XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record")) 

			if  (studentId.toString().equals(el.getAttributeValue("sid"))) 

				records.add(new  StudentUnitRecordProxy(new  Integer(el.getAttributeValue("sid")),  el.getAttributeValue("uid")));

		if  (records.size()  >  0) 

			studentRecord.put(studentId,  records); // be careful - this could be empty
		return  records;
	}

	public  void  saveRecord(IStudentUnitRecord  unitRecord)  {

		for  (Element  el  :  (List<Element>)  XMLManager.getXML().getDocument().getRootElement().getChild("studentUnitRecordTable").getChildren("record"))  {

			if  (unitRecord.getStudentID().toString().equals(el.getAttributeValue("sid"))  &&  unitRecord.getUnitCode().equals(el.getAttributeValue("uid")))  {

				el.setAttribute("asg1",  new Float(unitRecord.getAsg1()).toString());
				el.setAttribute("asg2",  new Float(unitRecord.getAsg2()).toString());
				el.setAttribute("exam",  new Float(unitRecord.getExam()).toString());
				XMLManager.getXML().saveDocument(); //write out the XML file for continuous save

				return;
			}

		}

		throw  new  RuntimeException("DBMD:  saveRecord:  no such  student  record  in  data");

	}

}
