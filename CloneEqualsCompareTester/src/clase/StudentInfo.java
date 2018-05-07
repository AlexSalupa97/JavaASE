package clase;

public class StudentInfo extends Student
{
	private String specializare;

	public StudentInfo(String nume, int mat, float medie,String specializare) 
	{
		super(nume,mat,medie);
		this.specializare = specializare;
	}
	
	
	public String getSpecializare() {
		return specializare;
	}

	public void setSpecializare(String specializare) {
		this.specializare = specializare;
	}

	@Override
	public StudentInfo clone() throws CloneNotSupportedException {
		
		StudentInfo si=(StudentInfo)super.clone();
		return si;
	}

	@Override
	public String toString() 
	{
		return super.toString()+" de la "+this.getSpecializare();
	}


	
}
