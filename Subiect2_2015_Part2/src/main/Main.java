package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import clase.Autovehicul;

public class Main
{

	public static void main(String[] args) 
	{
		List<Autovehicul> lista=new ArrayList<>();
		
		try {
			lista.add(new Autovehicul("Dacia", 1500, 4, 70));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.add(new Autovehicul("Ford", 1900, 4, 105));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.add(new Autovehicul("BMW", 2300, 4, 150));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.add(new Autovehicul("Opel", 1600, 4, 75));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lista.add(new Autovehicul("Koenigsegg", 2000, 4, 500));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try(OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("autovehicule.txt")))
		{
			Iterator<Autovehicul> it=lista.iterator();
			for(;it.hasNext();)
			{
				Autovehicul aux=it.next();
				writer.write(aux.getMarca()+" "+aux.getCaiPutere()+"\r\n");
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try(ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream("autovehicule.dat")))
		{
			writer.writeObject(lista);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Autovehicul> reconstituita=null;
		try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream("autovehicule.dat"))) 
		{
			reconstituita=(List<Autovehicul>)reader.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Autovehicul> itR=reconstituita.iterator();
		for(;itR.hasNext();)
			System.out.println(itR.next().getMarca());
		
		
		Callable<Integer> lessThan100=()->
		{ 
			Integer ct=0;
			for(int i=0;i<lista.size();i++)
			{
				if(lista.get(i).getViteza()<100)
				{
					System.out.println("Sub 100: "+lista.get(i).getMarca()+" "+Thread.currentThread().getName());
					ct++;
				}
			}
			return ct;
		};
		
		Callable<Integer> moreThan100=()->
		{ 
			Integer ct=0;
			for(int i=0;i<lista.size();i++)
			{
				if(lista.get(i).getViteza()>100)
				{
					System.out.println("Peste 100: "+lista.get(i).getMarca()+" "+Thread.currentThread().getName());
					ct++;
				}
			}
			return ct;
		};
		
		ExecutorService executor=Executors.newFixedThreadPool(2);
		Future<Integer> ctLessThan100=executor.submit(lessThan100);
		Future<Integer> ctMoreThan100=executor.submit(moreThan100);
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
