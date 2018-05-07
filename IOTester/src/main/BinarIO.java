package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import clase.Student;

public class BinarIO 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		Set<Student> set=new TreeSet<>();
		
		Student value;
		
		for(int i=1;i<=5;i++)
		{
			value=new Student(Integer.toString(i),i,i);
			set.add(value);
		}
		
//		////////////////////////////////////////////////////////////////

		try(DataOutputStream out=new DataOutputStream(new FileOutputStream("out.dat"))) 
		{
			Iterator<Student> it=set.iterator();	
			for(;it.hasNext();)
			{
				Student s=it.next();
				out.writeUTF(s.getNume());
				out.writeInt(s.getMatricol());
				out.writeDouble(s.getMedie());
			}
			out.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		////////////////////////////////////////////////////////////////		
		
		try(OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("outbinar.txt")))
		{
			DataInputStream reader = new DataInputStream(new FileInputStream("out.dat"));
			String nume="";
			int matr;
			double med;
			
			for(int i=0;i<5;i++)
			{
				nume=reader.readUTF();
				matr=reader.readInt();
				med=reader.readDouble();
				writer.write(nume+" "+matr+" "+med+"\r\n");
			}
			
			reader.close();
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
