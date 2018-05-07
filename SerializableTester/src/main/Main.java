package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import clase.Masina;
import clase.Motor;
import clase.Motor.TipCombustibil;

public class Main 
{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		Motor motor=new Motor();
		motor.setCombustibil(TipCombustibil.motorina);
		motor.setConsum(6.8);
		motor.setPutere(150);
		
		Masina masina=new Masina();
		masina.setMotor(motor);
		masina.setProducator("Ford");
		
		Masina masina2=new Masina();
		masina2.setMotor(motor);
		masina2.setProducator("Opel");
		
		List<Masina> list=new ArrayList<>();
		list.add(masina2);
		list.add(masina);
		
		
		
		try 
		{
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("masina.bin"));
			out.writeObject(list);
			out.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Masina> deserializata=null;
		try 
		{
			ObjectInputStream in=new ObjectInputStream(new FileInputStream("masina.bin"));
			deserializata=(List<Masina>)in.readObject();
			in.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(deserializata.get(0).getProducator());
		System.out.println(deserializata.get(0).getMotor().getClass());
	}

}
