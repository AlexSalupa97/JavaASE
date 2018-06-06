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

public class OperatiiInOutFisier implements OperatiiFisier, Comparable<OperatiiInOutFisier>, Cloneable
{
	private String numeFisier;
	private String tipObiect;

	public OperatiiInOutFisier() {
		this.numeFisier = "";
		this.tipObiect = "";
	}

	public OperatiiInOutFisier(String numeFisier, String tipObiect) {
		this.numeFisier = numeFisier;
		this.tipObiect = tipObiect;
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
		String deTokenizat = null;
		List<Avion> lista = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(this.numeFisier))) 
		{
			while (true) 
			{
				deTokenizat = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(deTokenizat);
				if (this.tipObiect.equals("AvionPasageri")) 
				{
					String serie = tokenizer.nextToken();
					float tonaj = Float.parseFloat(tokenizer.nextToken());
					String marca = tokenizer.nextToken();
					int nrLocuri = Integer.parseInt(tokenizer.nextToken());
					List<String> cnp = new Vector<>();
					while (tokenizer.hasMoreTokens()) 
					{
						cnp.add(tokenizer.nextToken());
					}
					AvionPasageri ap = new AvionPasageri(serie, tonaj, marca, nrLocuri, cnp);
					lista.add(ap);
				}
				else 
				{
					String serie = tokenizer.nextToken();
					float tonaj = Float.parseFloat(tokenizer.nextToken());
					String marca = tokenizer.nextToken();
					float capac = Float.parseFloat(tokenizer.nextToken());
					List<String> serieMrf = new Vector<>();
					while (tokenizer.hasMoreTokens()) 
					{
						serieMrf.add(tokenizer.nextToken());
					}
					AvionCargo ac = new AvionCargo(serie, tonaj, marca, capac, serieMrf);
					lista.add(ac);
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
			// TODO Auto-generated catch block
			
		}
		return lista;
	}
	
	

	@Override
	public void scrieObiectInFisierText(List<Avion> lista) 
	{
		Iterator<Avion> it=lista.iterator();
		try (OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(this.numeFisier)))
		{
			for(;it.hasNext();)
			{
				if(this.tipObiect.equals("AvionCargo"))
				{
					AvionCargo ac=(AvionCargo) it.next();
					writer.write(ac.getSerie()+" "+ac.getTonaj()+" "+ac.getMARCA()+" "+ac.getCapacitateTransportKg()+" ");
					Iterator<String> itS=ac.getSerieMarfuri().iterator();
					for(;itS.hasNext();)
						writer.write(itS.next());
					writer.write("\r\n");
				}
				if(this.tipObiect.equals("AvionPasageri"))
				{
					AvionPasageri ap=(AvionPasageri) it.next();
					writer.write(ap.getSerie()+" "+ap.getTonaj()+" "+ap.getMARCA()+" "+ap.getNrLocuri()+" ");
					Iterator<String> itS=ap.getCnpPasageri().iterator();
					for(;itS.hasNext();)
						writer.write(itS.next()+" ");
					writer.write("\r\n");
				}
			}
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

	@Override
	public Object clone() throws CloneNotSupportedException {
		OperatiiInOutFisier op=(OperatiiInOutFisier)super.clone();
		return op;
	}

	@Override
	public boolean equals(Object obj) 
	{
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
	public int compareTo(OperatiiInOutFisier o) 
	{
		
			File f1=new File(this.numeFisier);
			File f2=new File(o.numeFisier);
			return (int) (f1.length()-f2.length());

		
	}
	
	

	
}
