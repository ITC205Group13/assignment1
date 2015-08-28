package datamanagement;

public class CheckGradeCTL {
	CheckGradeUI checkGradeUI;
	String unitCode = null;
	Integer currentStudentID = null;
	boolean marksChanged = false;

	public CheckGradeCTL() {
	}

	public void execute() {
		checkGradeUI = new CheckGradeUI(this);
		checkGradeUI.setState1(false);
		checkGradeUI.setState2(false);
		checkGradeUI.setState3(false);
		checkGradeUI.setState4(false);
		checkGradeUI.setState5(false);
		checkGradeUI.setState6(false);
		checkGradeUI.Refresh3();
		ListUnitsCTL listUnitsCTIL = new ListUnitsCTL();
		listUnitsCTIL.listUnits(checkGradeUI);
		checkGradeUI.setVisible(true);
		checkGradeUI.setState1(true);
	}

	public void unitSelected(String code) {
		if (code.equals("NONE"))
			checkGradeUI.setState2(false);
		else {
			ListStudentsCTL listStudentsCTL = new ListStudentsCTL();
			listStudentsCTL.listStudents(checkGradeUI, code);
			unitCode = code;
			checkGradeUI.setState2(true);
		}
		checkGradeUI.setState3(false);
	}

	public void studentSelected(Integer studentId) {
		currentStudentID = studentId;
		if (currentStudentID.intValue() == 0) {
			checkGradeUI.Refresh3();
			checkGradeUI.setState3(false);
			checkGradeUI.setState4(false);
			checkGradeUI.setState5(false);
			checkGradeUI.setState6(false);
		}
		else {
			IStudent student = StudentManager.get().getStudent(studentId);
			IStudentUnitRecord studentUnitRecord = student.getUnitRecord(unitCode);
			checkGradeUI.setRecord(studentUnitRecord);
			checkGradeUI.setState3(true);
			checkGradeUI.setState4(true);
			checkGradeUI.setState5(false);
			checkGradeUI.setState6(false);
			marksChanged = false;
		}
	}

	public String checkGrade(float asg1Mark, float asg2Mark, float examMark) {
		IUnit unit = UnitManager.unitMap().getUnit(unitCode);
		String grade = unit.getGrade(asg1Mark, asg2Mark, examMark);
		checkGradeUI.setState4(true);
		checkGradeUI.setState5(false);
		if (marksChanged) {
			checkGradeUI.setState6(true);
		}
		return grade;
	}

	public void enableChangeMarks() {
		checkGradeUI.setState4(false);
		checkGradeUI.setState6(false);
		checkGradeUI.setState5(true);
		marksChanged = true;
	}

	public void saveGrade(float asg1Mark, float asg2Mark, float examMark) {
		IUnit unit = UnitManager.unitMap().getUnit(unitCode);
		IStudent student = StudentManager.get().getStudent(currentStudentID);
		IStudentUnitRecord studentUnitRecord = student.getUnitRecord(unitCode);
		studentUnitRecord.setAsg1(asg1Mark);
		studentUnitRecord.setAsg2(asg2Mark);
		studentUnitRecord.setExam(examMark);
		StudentUnitRecordManager.instance().saveRecord(studentUnitRecord);
		checkGradeUI.setState4(true);
		checkGradeUI.setState5(false);
		checkGradeUI.setState6(false);
	}
}
