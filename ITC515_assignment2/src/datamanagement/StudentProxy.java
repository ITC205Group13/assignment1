package  datamanagement;

public  class  StudentProxy  implements  IStudent  {

	private  Integer  studentId;
	private  String  firstName;
	private  String  lastName;
	private  StudentManager  studentManager;

	public StudentProxy(Integer studentId,  String  firstName,  String  lastName)  {

		this.studentId  =  studentId;
		this.firstName  = firstName;
		this.lastName  =  lastName;
		this.studentManager  =  StudentManager.get();

	}


	public  Integer  getID()  { 

		return  studentId; 

	}


	public  String  getFirstName()  { 

		return  firstName;

	}


	public  String  getLastName()  {

		return  lastName;

	}


	public  void  setFirstName(String firstName)  {

		studentManager.getStudent(studentId).setFirstName(firstName);

	}


	public  void  setLastName(String lastName)  {

		studentManager.getStudent(studentId).setLastName(lastName);

	}


	public  void  addUnitRecord(IStudentUnitRecord record)  {

		studentManager.getStudent(studentId).addUnitRecord(record);

	}


	public  IStudentUnitRecord  getUnitRecord(String unitCode)  {

		return  studentManager.getStudent(studentId).getUnitRecord(unitCode);

	}


	public  StudentUnitRecordList  getUnitRecords()  { 

		return  studentManager.getStudent(studentId).getUnitRecords();

	}

}

