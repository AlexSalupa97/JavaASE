package clase;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AvionPasageri extends Avion implements Cloneable, Serializable
{

	private static final long serialVersionUID = 3L;
	private int nrLocuri;
	private List<String> cnpPasageri =new Vector<>();

	

	public AvionPasageri() {
		super(null, 0, null);
		this.nrLocuri=0;
		this.cnpPasageri=null;
	}

	@XmlElement
	public int getNrLocuri() {
		return nrLocuri;
	}

	public void setNrLocuri(int nrLocuri) {
		this.nrLocuri = nrLocuri;
	}

	@XmlElement
	public List<String> getCnpPasageri() {
		return cnpPasageri;
	}

	public void setCnpPasageri(List<String> cnpPasageri) {
		this.cnpPasageri = cnpPasageri;
	}

	public AvionPasageri(String serie, float tonaj, String MARCA, int nrLocuri, List<String> cnpPasageri) throws Exception {
		super(serie, tonaj, MARCA);
		this.nrLocuri = nrLocuri;
		this.cnpPasageri = cnpPasageri;
		if(tonaj<0)
			throw new Exception("Tonaj negativ");	
	}

	@Override
	public double getCapacitate() {
		return this.nrLocuri;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AvionPasageri ap=(AvionPasageri)super.clone();
		List<String> cnp=new Vector<String>(this.cnpPasageri);
		ap.cnpPasageri=cnp;
		return ap;
		
	}

	@Override
	public boolean equals(Object obj)
	{
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
		
			
		
		
}
	
	
	
