package  datamanagement;

public  class  Unit  implements  IUnit  {
	
	private  String  unitCode;
	private  String  unitName;
	private  float  cutOff2;
	private  float  cutOff1;
	private  float  cutOff4;
	private  float  cutOff3;
	private  float  cutOff5;
	
	private  int  assignment1,  assignment2,  exam;
	
	private  StudentUnitRecordList  records;

	public  Unit(String  subjectCode, String  subjectName,  float  cutOff1, float  cutOff2, float  cutOff3, float  cutOff4,
			float  cutOff5,  int  assessmentWeight1,  int  assessmentWeight2,  int  examWeight,  StudentUnitRecordList  recordList) {

		unitCode = subjectCode;
		unitName = subjectName;
		
		cutOff2  =  cutOff1;
		cutOff1  =  cutOff2;
		this.cutOff4  =  cutOff3;
		cutOff3  =  cutOff4;
		this.cutOff5  =  cutOff5;
		
		this.setAssessmentWeights(assessmentWeight1,  assessmentWeight2,  examWeight);
		
		records  =  recordList  ==  null  ?  new  StudentUnitRecordList()  :  recordList;
	}

	public  String  getUnitCode()  {
		
		return  this.unitCode;
	}

	public  String  getUnitName()  {

		return  this.unitName;
	}

	public  void  setPsCutoff(float  cutoff)  {
		
		this.cutOff2  =  cutoff;
	}

	public  float  getPsCutoff()  {
		
		return  this.cutOff2;
	}

	public  void  setCrCutoff(float  cutoff)  {
		
		this.cutOff1  =  cutoff;
	}

	public  float  getCrCutoff()   {
		
		return this.cutOff1;
	}

	public  void  setDiCutoff(float  cutoff)  {
		this.cutOff4  =  cutoff;
	}

	public  float  getDiCutoff() {
		return  this.cutOff4;
	}

	public  void  hdCutoff(float  cutoff)  {
		
		this.cutOff3  =  cutoff;
	}

	public  void  setHdCutoff(float  cutoff)  {
		
		this.cutOff3  =  cutoff;
	}

	public  float  getHdCutoff()  {
		
		return  this.cutOff3;

	}

	public  void  setAeCutoff(float  cutoff)  {
		
		this.cutOff5  =  cutoff;
	}

	public  float  getAeCutoff()  {
		
		return  this.cutOff5;
	}

	public  void  addStudentRecord(IStudentUnitRecord  record)  {
		
		records.add(record);
	}

	public  IStudentUnitRecord  getStudentRecord(int  studentId)  {
		
		for  (IStudentUnitRecord  r  :  records)  {
			
			if  (r . getStudentID()  ==  studentId)
				
				return  r;
		}
		
		return  null;
	}

	public  StudentUnitRecordList  listStudentRecords()  {
		
		return records;
		
	}

	@Override
	public  int  getAsg1Weight()  {
		return  assignment1;
	}

	@Override
	public  int  getAsg2Weight()  {
		return  assignment2;
	}

	@Override
	public  int  getExamWeight()  {
		return  exam;
	}

	@Override
	public  void  setAssessmentWeights(int  assignment1, int  assignment2, int  exam)  {
		
		if  (assignment1  <  0  ||  assignment2  >  100  ||
				assignment2  <  0  ||  assignment2  >  100 ||
			 exam  <  0  ||  exam  >  100)  {
			
			throw  new  RuntimeException("Assessment  weights  cant  be less  than  zero  or  greater  than  100");
		}
		
		if  (assignment1  +  assignment2  +  exam  !=  100)  {
			
			throw  new  RuntimeException("Assessment  weights  must  add  to  100");
		}
		
		this.assignment1  =  assignment1;
		this.assignment2  =  assignment2;
		this.exam  =  exam;		
		
	}
	
	private  void  setCutoffs(float  pass,  float  credit,  float  distinction,  float  hd,  float  advance)  {
		
		if  (pass  <  0  ||  pass  >  100  ||
			 credit  <  0  ||  credit  >  100  ||
			 distinction  <  0   ||   distinction  >  100 ||
			 hd  <  0  ||  hd  >  100  ||
			 advance  <  0  ||  advance   >  100)  {
			
			 throw  new  RuntimeException("Assessment  cutoffs  cant be  less  than  zero  or  greater  than  100");
		}
		
		if  (advance  >=  pass)  {
			
			throw  new  RuntimeException("ADVANCE  cutoff  must  be  less  than  PASS  cutoff");
		}
		
		if  (pass  >=  credit)  {
			
			throw  new  RuntimeException("PASS  cutoff  must  be  less  than  CREDIT  cutoff");
		}
		
		if  (credit  >=  distinction)  {
			
			throw  new  RuntimeException("CREDIT  cutoff  must  be  less  than DISTINCTION cutoff");
		}
		 
		if  (distinction  >=  hd)  {
			
			throw  new  RuntimeException("DISTINCTION  cutoff  must  be  less  than  HD  cutoff");
		}

	}
	
	public  String  getGrade(float  score1, float  score2, float  score3)  {
		
		float  totalScore  = score1  +  score2 +  score3;
		
		if  (score1  <  0  ||  score1  >  assignment1  ||
				score2  <  0  ||  score2  >  assignment2  ||
				score3  <  0  ||  score3  >  exam )  {
			
			throw  new  RuntimeException("marks  cannot  be  less  than  zero  or  greater  than  assessment  weights");
		} 

		if  (totalScore  <  cutOff5)  {
			
			return   "FL"; 
			
		} 
		
		else  if  (totalScore  <  cutOff2)
			
			return "AE";
		
		else  if  (totalScore < cutOff1)
			
			return   "PASS";
		
		else  if  (totalScore  <  cutOff4)
			
			return  "CR";
		
		else  if (totalScore  <  cutOff3)
			
			return  "DI";
		
		else
			
			return  "HD";
	}

	
}