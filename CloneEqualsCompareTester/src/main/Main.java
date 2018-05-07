package main;

import clase.Student;
import clase.StudentInfo;

public class Main 
{
	public static void main(String[] args)
	{
		Student s1=new Student("1",1,1);
		Student s2=new Student("2",2,2);
		System.out.println(s1.compareTo(s2));
		Student s3=null;
		
		try 
		{
			s3=s1.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		s3.setMatricol(3);
		System.out.println(s3.getClass());
		
		StudentInfo si1=new StudentInfo("1",1,1,"IE");
		StudentInfo si2=null;
		
		try 
		{
			si2=si1.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s1.equals(s3));
		System.out.println(si2.getSpecializare());
		System.out.println(s1.toString());
		System.out.println(si1.toString());
	}
}
