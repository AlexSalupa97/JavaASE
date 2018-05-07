package clase;

public class Student implements Comparable<Student>,Cloneable
{
	private String nume;
	private int matricol;
	private float medie;

	public Student()
	{
		nume="";
		matricol=0;
		medie=0;
	}
	public Student(String num, int matr, float med)
	{
		this.nume=num;
		this.matricol=matr;
		this.medie=med;
	}
	
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public int getMatricol() {
		return matricol;
	}
	public void setMatricol(int matricol) {
		this.matricol = matricol;
	}
	public float getMedie() {
		return medie;
	}
	public void setMedie(float medie) {
		this.medie = medie;
	}
	
	
	@Override
	public int compareTo(Student o) 
	{
		if(this.medie>o.medie)
			return 1;
		else
			if(this.medie<o.medie)
				return -1;
		return 0;
	}
	
	@Override
	public Student clone() throws CloneNotSupportedException {
		
		Student s=(Student)super.clone();
		return s;
	}
	
	
	@Override
	public boolean equals(Object obj) 
	{
		if(this==obj)
			return true;
		if(!(obj instanceof Student))
			return false;
		else
		{
			Student copie=(Student)obj;
			if(copie.matricol==this.matricol)
				return true;
			else
				return false;
		}
	}
	
	
	@Override
	public String toString() 
	{
		return "Studentul "+this.nume;
	}

	
}
