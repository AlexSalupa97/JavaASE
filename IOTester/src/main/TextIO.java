package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import clase.Student;

@SuppressWarnings("unused")
public class TextIO 
{

	public static void main(String[] args) 
	{
		Set<Student> set=new TreeSet<>();
		
		Student value;
		
		for(int i=1;i<=5;i++)
		{
			value=new Student(Integer.toString(i),i,i);
			set.add(value);
		}
		
		Iterator<Student> it=set.iterator();	
//		for(;it.hasNext();)
//			System.out.println(it.next().toString());
		
		
		
//		////////////////////////////////////////////////////////////////
		
//		try(OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("out.txt")))
//		{	
//			for(;it.hasNext();)
//			{
//				writer.write(it.next().toString()+"\r\n");
//				
//			}
//			writer.close();
//		} 
//		catch (FileNotFoundException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (IOException e1) 
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
//		////////////////////////////////////////////////////////////////
				
//		String citit="";
//		
//		
//		try(BufferedReader buff=new BufferedReader(new FileReader("out.txt")))
//		{
//			for(int i=set.size();i>0;i--)
//			{
//				citit+=buff.readLine();
//				citit+="\r\n";
//			}
//			buff.close();
//		} 
//		catch (FileNotFoundException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (IOException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		System.out.println(citit);
		
		
		
	}

}
