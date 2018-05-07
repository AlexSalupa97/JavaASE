package main;

import java.io.DataOutputStream;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import clase.CalatorieVacanta;

@SuppressWarnings("unused")
public class Main 
{
	public static double minim(List<CalatorieVacanta> lista,int i)
	{
		double minim=lista.get(i).getCheltuieli();
		for(int j=i;j<lista.size();j=j+2)
			if(minim>lista.get(j).getCheltuieli())
				minim=lista.get(j).getCheltuieli();
		return minim;
	}
	
	
	public static void main(String[] args) 
	{
		List<CalatorieVacanta> lista = new ArrayList<>();

		lista.add(new CalatorieVacanta("1", 1, "1", 1));
		lista.add(new CalatorieVacanta("2", 2, "2", 2));
		lista.add(new CalatorieVacanta("3", 3, "3", 3));
		lista.add(new CalatorieVacanta("4", 4, "4", 4));
		lista.add(new CalatorieVacanta("5", 5, "5", 5));
		
		
		CalatorieVacanta cv=null;
		try(OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream("listaCalatoriiVacanta.txt")))
		{
			Iterator<CalatorieVacanta> it=lista.iterator();
			for(;it.hasNext();)
			{
				cv=it.next();
				writer.write(cv.toString()+"\r\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try(ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream("listaCalatoriiVacanta.dat")))
		{
			writer.writeObject(lista);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		List<CalatorieVacanta> restaurata=null;
		try {
			ObjectInputStream reader=new ObjectInputStream(new FileInputStream("listaCalatoriiVacanta.dat"));
			restaurata=(List<CalatorieVacanta>)reader.readObject();
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<CalatorieVacanta> itR=restaurata.iterator();
		for(;itR.hasNext();)
			System.out.println(itR.next().getCheltuieli());
		
		ExecutorService executor=Executors.newFixedThreadPool(2);
		final List<CalatorieVacanta> finala=restaurata;
		
		Runnable par=()->
		{
			for(int i=0;i<finala.size();i++)
				if(i%2==0)
					System.out.println("Par: "+finala.get(i).getCheltuieli()+" "+Thread.currentThread().getId());
			System.out.println(Thread.currentThread().getName()+" -- Minim: "+minim(finala,0));
		};
		
		Runnable impar=()->
		{
			for(int i=0;i<finala.size();i++)
				if(i%2!=0)
					System.out.println("Impar: "+finala.get(i).getCheltuieli()+" "+Thread.currentThread().getId());
			System.out.println(Thread.currentThread().getName()+" -- Minim: "+minim(finala,1));
		};
		
		executor.execute(par);
		executor.execute(impar);
		
		executor.shutdown();
		try 
		{
			executor.awaitTermination(Integer.MAX_VALUE,TimeUnit.MILLISECONDS);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		Callable<Double> callable=()->{return minim(finala,0);};
		ExecutorService callableFuture=Executors.newSingleThreadExecutor();
		Future<Double> future=callableFuture.submit(callable);
		
		Double minimPar=0.0;
		
		try {
			minimPar=future.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Minim callable: "+minimPar);
		
		
		
	}
}
