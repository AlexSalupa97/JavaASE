package clase;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class TrenPasageri extends Tren implements Serializable,Cloneable
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2911035606665902211L;
	private int nrLocuri;
	private List<String> cnpPasageri=new Vector<>();
	
	public TrenPasageri(String serie, float tonaj, String marca, int nrLocuri, List<String> cnpPasageri) throws Exception {
		super(serie, tonaj, marca);
		this.nrLocuri = nrLocuri;
		this.cnpPasageri = cnpPasageri;
		if(tonaj<0)
			throw new Exception("meh");
	}

	public int getNrLocuri() {
		return nrLocuri;
	}

	public List<String> getCnpPasageri() {
		return cnpPasageri;
	}

	@Override
	public double getCapacitate() {
		// TODO Auto-generated method stub
		return nrLocuri;
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		TrenPasageri tp=(TrenPasageri)super.clone();
		List<String> lista=new Vector<>(this.cnpPasageri);
		tp.cnpPasageri=lista;
		return tp;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof TrenPasageri))
			return false;
		else
		{
			TrenPasageri tp=(TrenPasageri)obj;
			if(this.nrLocuri==tp.nrLocuri)
				return true;
			
		}
		return false;
	}
	
	
}
