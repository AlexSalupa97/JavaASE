package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

import clase.Matrice;
import clase.TitluCalatorieMetropolitan;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		Object[][] o= {{1.0,2.0},{3.0,4.0}};
		Matrice m1= new Matrice(2,2,o);
		
		Matrice m2=null;
		
		try 
		{
			m2=(Matrice) m1.clone();
		} 
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("fisier.txt"));
			writer.write("this is a test");
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String deTokenizat=null;
		
		try 
		{
			BufferedReader reader=new BufferedReader(new FileReader("fisier.txt"));
			deTokenizat=reader.readLine();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		StringTokenizer tokenizer=new StringTokenizer(deTokenizat);
		
		while(tokenizer.hasMoreTokens())
			System.out.println(tokenizer.nextToken());
	}

}
