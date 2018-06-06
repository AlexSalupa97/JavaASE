package clase;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class AvionPasageri extends Avion implements Serializable, Cloneable
{
	
	private static final long serialVersionUID = -4842169647777653401L;
	private int nrLocuri;
	private List<String> cnpPasageri=new Vector<>();
	
	public AvionPasageri(String serie, float tonaj, String mARCA, int nrLocuri, List<String> cnpPasageri) throws Exception {
		super(serie, tonaj, mARCA);
		this.nrLocuri = nrLocuri;
		this.cnpPasageri = cnpPasageri;
		if(tonaj<0)
			throw new Exception("Tonaj negativ");
	}
	
	

	public AvionPasageri() {
		super(null, 0, null);
		this.nrLocuri = 0;
		this.cnpPasageri = null;
	}



	@Override
	public double getCapacitate() {
		
		return nrLocuri;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AvionPasageri ap=(AvionPasageri)super.clone();
		List<String> lista=new Vector<>(this.cnpPasageri);
		ap.cnpPasageri=lista;
		return ap;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof AvionPasageri))
			return false;
		else
		{
			AvionPasageri ap=(AvionPasageri)obj;
			if(this.nrLocuri==ap.nrLocuri)
				return true;
		}
		return false;
	}

	public int getNrLocuri() {
		return nrLocuri;
	}

	public void setNrLocuri(int nrLocuri) {
		this.nrLocuri = nrLocuri;
	}

	public List<String> getCnpPasageri() {
		return cnpPasageri;
	}

	public void setCnpPasageri(List<String> cnpPasageri) {
		this.cnpPasageri = cnpPasageri;
	}
	
	
	
	

}
