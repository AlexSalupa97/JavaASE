package clase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class OperatiiInOutFisier implements OperatiiFisier,Cloneable,Comparable<OperatiiInOutFisier> {

	private String numeFisier;
	private String tipObiect;
	
	
	
	public OperatiiInOutFisier(String numeFisier, String tipObiect) {
		this.numeFisier = numeFisier;
		this.tipObiect = tipObiect;
	}

	public OperatiiInOutFisier() 
	{
		this.numeFisier="";
		this.tipObiect="";
	}
	
	

	public String getNumeFisier() {
		return numeFisier;
	}

	public void setNumeFisier(String numeFisier) {
		this.numeFisier = numeFisier;
	}

	public String getTipObiect() {
		return tipObiect;
	}

	public void setTipObiect(String tipObiect) {
		this.tipObiect = tipObiect;
	}

	@Override
	public List<Avion> citesteObiectDinFisierText() 
	{
		List<Avion> lista=new ArrayList<>();
		String deTokenizat=null;
		
		try (BufferedReader reader=new BufferedReader(new FileReader(this.numeFisier)))
		{
			while(true)
			{
				deTokenizat=reader.readLine();
				if(deTokenizat==null)
					break;
				StringTokenizer tokenizer=new StringTokenizer(deTokenizat);
				if(this.tipObiect.equals("AvionPasageri"))
				{
					String serie=tokenizer.nextToken();
					float tonaj=Float.parseFloat(tokenizer.nextToken());
					String marca=tokenizer.nextToken();
					int nr=Integer.parseInt(tokenizer.nextToken());
					List<String> cnp=new Vector<>();
					while(tokenizer.hasMoreTokens())
					{
						cnp.add(tokenizer.nextToken());
					}
					AvionPasageri ap=new AvionPasageri(serie, tonaj, marca, nr, cnp);
					lista.add(ap);
				}
				if(this.tipObiect.equals("AvionCargo"))
				{
					String serie=tokenizer.nextToken();
					float tonaj=Float.parseFloat(tokenizer.nextToken());
					String marca=tokenizer.nextToken();
					float capacitate=Float.parseFloat(tokenizer.nextToken());
					List<String> serieMrf=new Vector<>();
					while(tokenizer.hasMoreTokens())
					{
						serieMrf.add(tokenizer.nextToken());
					}
					AvionCargo ap=new AvionCargo(serie, tonaj, marca, capacitate, serieMrf);
					lista.add(ap);
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
		}
		return lista;
	}

	@Override
	public void scrieObiectInFisierText(List<Avion> lista) 
	{
		Iterator<Avion> it=lista.iterator();
		try(OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(this.numeFisier)))
		{
			for(;it.hasNext();)
			{
				if(this.tipObiect.equals("AvionPasageri"))
				{
					AvionPasageri ap=(AvionPasageri)it.next();
					writer.write(ap.getSerie()+" "+ap.getTonaj()+" "+ap.getMARCA()+" "+ap.getNrLocuri()+" ");
					Iterator<String> its=ap.getCnpPasageri().iterator();
					for(;its.hasNext();)
						writer.write(its.next()+" ");
					writer.write("\r\n");
				}
				if(this.tipObiect.equals("AvionCargo"))
				{
					AvionCargo ap=(AvionCargo)it.next();
					writer.write(ap.getSerie()+" "+ap.getTonaj()+" "+ap.getMARCA()+" "+ap.getCapacitate()+" ");
					Iterator<String> its=ap.getSerieMarfuri().iterator();
					for(;its.hasNext();)
						writer.write(its.next()+" ");
					writer.write("\r\n");
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		OperatiiInOutFisier op=(OperatiiInOutFisier)super.clone();
		return op;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof OperatiiInOutFisier))
			return false;
		else
		{
			OperatiiInOutFisier op=(OperatiiInOutFisier)obj;
			if(this.numeFisier==op.numeFisier&&this.tipObiect==op.tipObiect)
				return true;
			
		}
		return false;
	}

	@Override
	public int compareTo(OperatiiInOutFisier o) {
		File f1=new File(this.numeFisier);
		File f2=new File(o.numeFisier);
		return (int)(f1.length()-f2.length());
	}
	
	

}
