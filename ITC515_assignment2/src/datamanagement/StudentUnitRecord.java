package datamanagement;

public class StudentUnitRecord implements IStudentUnitRecord {
	private Integer studentId;
	private String unitCode;
	private float asg1Mark, asg2Mark, examMark;

	public StudentUnitRecord(Integer studentId, String unitCode, float asg1Mark, float asg2Mark, float examMark) {
		this.studentId = studentId;
		this.unitCode = unitCode;
		this.setAsg1(asg1Mark);
		this.setAsg2(asg2Mark);
		this.setExam(examMark);
	}

	public Integer getStudentID() {
		return studentId;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setAsg1(float asg1Mark) {
		if (asg1Mark < 0 || asg1Mark > UnitManager.UM().getUnit(unitCode).getAsg1Weight()) {
			throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
		}
		this.asg1Mark = asg1Mark;
	}

	public float getAsg1() {
		return asg1Mark;
	}

	public void setAsg2(float asg2Mark) {
		if (asg2Mark < 0 || asg2Mark > UnitManager.UM().getUnit(unitCode).getAsg2Weight()) {
			throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
		}
		this.asg2Mark = asg2Mark;
	}

	public float getAsg2() {
		return asg2Mark;
	}

	public void setExam(float examMark) {
		if (examMark < 0 || examMark > UnitManager.UM().getUnit(unitCode).getExamWeight()) {
			throw new RuntimeException("Mark cannot be less than zero or greater than assessment weight");
		}
		this.examMark = examMark;
	}

	public float getExam() {
		return examMark;
	}

	public float getTotal() {
		return asg1Mark + asg2Mark + examMark;
	}
}
