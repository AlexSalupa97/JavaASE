package clase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class OperatiiInOutFisier implements OperatiiFisier, Cloneable, Comparable<OperatiiInOutFisier>
{
	private String numeFisier;
	private String tipFisier;
	
	public OperatiiInOutFisier(String numeFisier, String tipFisier) {
		super();
		this.numeFisier = numeFisier;
		this.tipFisier = tipFisier;
	}

	public OperatiiInOutFisier() {
		super();
	}

	public String getNumeFisier() {
		return numeFisier;
	}

	public void setNumeFisier(String numeFisier) {
		this.numeFisier = numeFisier;
	}

	public String getTipFisier() {
		return tipFisier;
	}

	public void setTipFisier(String tipFisier) {
		this.tipFisier = tipFisier;
	}

	@Override
	public int compareTo(OperatiiInOutFisier o) {
		File f=new File(this.numeFisier);
		File f1=new File(o.numeFisier);
		return (int)(f.length()-f1.length());
	}

	@Override
	public List<Tren> citesteObiectDinFisierText() 
	{
		List<Tren> lista=new ArrayList<>();
		String deTokenizat=null;
		try(BufferedReader reader=new BufferedReader(new FileReader(this.numeFisier))) 
		{
			while(true)
			{
				deTokenizat=reader.readLine();
				if(deTokenizat==null)
					break;
				StringTokenizer tokenizer=new StringTokenizer(deTokenizat);
				if(this.tipFisier.equals("TrenPasageri"))
				{
					String serie=tokenizer.nextToken();
					float tonaj=Float.parseFloat(tokenizer.nextToken());
					String marca=tokenizer.nextToken();
					int nrLocuri=Integer.parseInt(tokenizer.nextToken());
					List<String> cnp=new Vector<>();
					while(tokenizer.hasMoreTokens())
						cnp.add(tokenizer.nextToken());
					TrenPasageri tp=new TrenPasageri(serie, tonaj, marca, nrLocuri, cnp);
					lista.add(tp);
				}
				if(this.tipFisier.equals("TrenCargo"))
				{
					String serie=tokenizer.nextToken();
					float tonaj=Float.parseFloat(tokenizer.nextToken());
					String marca=tokenizer.nextToken();
					float capacitate=Float.parseFloat(tokenizer.nextToken());
					List<String> cnp=new Vector<>();
					while(tokenizer.hasMoreTokens())
						cnp.add(tokenizer.nextToken());
					TrenCargo tp=new TrenCargo(serie, tonaj, marca, capacitate, cnp);
					lista.add(tp);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}
	

	@Override
	public void scrieObiectInFisierText(List<Tren> lista) 
	{
		try(OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(this.numeFisier)))
		{
			for(Tren t:lista)
			{
				if(this.tipFisier.equals("TrenPasageri"))
				{
					TrenPasageri tp=(TrenPasageri)t;
					writer.write(tp.getSerie()+" "+tp.getTonaj()+" "+tp.getMarca()+" "+tp.getNrLocuri()+" ");
					for(String s:tp.getCnpPasageri())
						writer.write(s+" ");
					writer.write("\r\n");
				}
				else
				{
					TrenCargo tp=(TrenCargo)t;
					writer.write(tp.getSerie()+" "+tp.getTonaj()+" "+tp.getMarca()+" "+tp.getCapacitateTransportKg()+" ");
					for(String s:tp.getSerieMarfuri())
						writer.write(s+" ");
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
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
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
			OperatiiInOutFisier tp=(OperatiiInOutFisier)obj;
			if(this.numeFisier==tp.numeFisier)
				return true;
			
		}
		return false;
	}
	
	

}
