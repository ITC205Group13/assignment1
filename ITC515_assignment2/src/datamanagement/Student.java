package datamanagement;
public class Student implements IStudent {
    private Integer studentId; 
    private String firstName;
    private String lastName;
    private StudentUnitRecordList recordList;

    public Student (Integer studentId, String firstName, String lastName, StudentUnitRecordList record) { 
    	this.studentId = studentId; 
    	this.firstName = firstName;
        this.lastName = lastName;
        this.recordList = recordList == null ? new StudentUnitRecordList() : record;
    }

    public Integer getID() { 
    	return this.studentId; 
    } 
    
    public String getFirstName() { 
    	return firstName; 
    }

    public void setFirstName (String firstName) { 
    	this.firstName = firstName; 
    }

    public String getLastName() { 
    	return lastName; 
    }
    
    public void setLastName (String lastName) { 
    	this.lastName = lastName; 
    }

    public void addUnitRecord (IStudentUnitRecord record) { 
    	recordList.add(record); 
    }
        
    public IStudentUnitRecord getUnitRecord (String unitCode) {
    	for (IStudentUnitRecord r : recordList)  
            if (r.getUnitCode().equals(unitCode)) 
            	return r; 
    		return null;
    }

    public StudentUnitRecordList getUnitRecords() { 
    	return recordList; 
	}
}
