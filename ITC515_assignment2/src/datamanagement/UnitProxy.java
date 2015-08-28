package datamanagement;

public class UnitProxy implements IUnit {
	private String unitCode;
    private String unitName;    
    UnitManager unitManager;

    public UnitProxy (String unitCode, String unitName) {
        this.unitCode = unitCode;
        this.unitName = unitName;
        unitManager = UnitManager.unitMap(); //update according to changes
    }
    
    public String getUnitCode() { 
        return this.unitCode;
    }
        
    public String getUnitName() { 
        return this.unitName; 
    }
    
    public void setPsCutoff (float cutoff) {  //update according to changes
        unitManager.getUnit(unitCode).setPsCutoff(cutoff);
    }
    
    public float getPsCutoff() {
        return unitManager.getUnit(unitCode).getPsCutoff();
    }
    
    public void setCrCutoff (float cutoff) {
    	unitManager.getUnit(unitCode).setCrCutoff(cutoff);
    }
    
    public float getCrCutoff() {
    	return unitManager.getUnit(unitCode).getCrCutoff();
    }

    public void setDiCutoff (float cutoff) {
    	unitManager.getUnit(unitCode).setDiCutoff(cutoff);
    }
    
    public float getDiCutoff() {
    	return unitManager.getUnit(unitCode).getDiCutoff();
    }
    public void setHdCutoff (float cutoff) {
    	unitManager.getUnit(unitCode).setHdCutoff(cutoff);
    }
    
    public float getHdCutoff() {
    	return unitManager.getUnit(unitCode).getHdCutoff();
    }
    
    public void setAeCutoff (float cutoff) {
    	unitManager.getUnit(unitCode).setAeCutoff(cutoff);
    }
    
    public float getAeCutoff() {
    	return unitManager.getUnit(unitCode).getAeCutoff();
    }

    public String getGrade (float asg1, float asg2, float exam) {	
    	return unitManager.getUnit(unitCode).getGrade(asg1, asg2, exam);
    }
    
    public void addStudentRecord (IStudentUnitRecord record) { 
    	unitManager.getUnit(unitCode).addStudentRecord(record);
    }
    
    public IStudentUnitRecord getStudentRecord (int student) {
    	return unitManager.getUnit(unitCode).getStudentRecord(student);
    }
    
    public StudentUnitRecordList listStudentRecords() {
    	return unitManager.getUnit(unitCode).listStudentRecords();
    }
    
    public int getAsg1Weight() {
    	return unitManager.getUnit(unitCode).getAsg1Weight();
    }

    public int getAsg2Weight() {
    	return unitManager.getUnit(unitCode).getAsg2Weight();
    }
    
    public int getExamWeight() {
    	return unitManager.getUnit(unitCode).getExamWeight();
    }
    
    public void setAssessmentWeights (int asg1Wgt, int asg2Wgt, int examWgt) {
    	unitManager.getUnit(unitCode).setAssessmentWeights(asg1Wgt, asg2Wgt, examWgt);
	}
}
