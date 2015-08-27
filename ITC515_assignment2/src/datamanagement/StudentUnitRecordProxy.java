package datamanagement;
public class StudentUnitRecordProxy implements IStudentUnitRecord {
	private Integer studentID;
	private String unitCode;
	private StudentUnitRecordManager manager;
	
	public StudentUnitRecordProxy (Integer studentId, String unitCode) {
		this.studentID = studentId;
		this.unitCode = unitCode;
		this.manager = StudentUnitRecordManager.instance();
	}
	
	public Integer getStudentID() { 
		return studentID;
	}
	
	public String getUnitCode() { 
		return unitCode; 
	}

	public void setAsg1 (float mark) {
		manager.getStudentUnitRecord (studentID, unitCode).setAsg1(mark);
	}
	
	public float getAsg1() {
		return manager.getStudentUnitRecord (studentID, unitCode).getAsg1();
	}
	
	public void setAsg2 (float mark) { 
		manager.getStudentUnitRecord (studentID, unitCode).setAsg2(mark);
	}

	public float getAsg2() {
		return manager.getStudentUnitRecord (studentID, unitCode).getAsg2();
	}

	public void setExam (float mark) {
		manager.getStudentUnitRecord (studentID, unitCode).setExam(mark);
	}

	public float getExam() {
		return manager.getStudentUnitRecord (studentID, unitCode).getExam();
	}
	
	public float getTotal() {
		return manager.getStudentUnitRecord (studentID, unitCode).getTotal();
	}
}
