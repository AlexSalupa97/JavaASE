package clase;

import java.io.Serializable;

public class CalatorieVacanta extends Calatorie implements Tripable,Cloneable,Serializable
{
	private static final long serialVersionUID = 1L;
	private float costuriZilnice;
	
	public CalatorieVacanta()
	{
		
	}

	public CalatorieVacanta(String destinatie, int nr_zile, String mod_transport, float costuriZilnice) {
		super(destinatie, nr_zile, mod_transport);
		this.costuriZilnice = costuriZilnice;
	}

	public float getCosturiZilnice() {
		return costuriZilnice;
	}

	public void setCosturiZilnice(float costuriZilnice) {
		this.costuriZilnice = costuriZilnice;
	}

	@Override
	public String calatoreste() {
		return "vacanta";
	}

	@Override
	public float getCheltuieli() {
		return this.nr_zile*this.costuriZilnice;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		CalatorieVacanta cv=(CalatorieVacanta) super.clone();
		return cv;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if(this==obj)
			return true;
		if(!(obj instanceof CalatorieVacanta))
			return false;
		else
		{
			CalatorieVacanta cv=(CalatorieVacanta)obj;
			if(this.costuriZilnice==cv.costuriZilnice)
				return true;
			else
				return false;
		}
		
	}

	public int compareTo(CalatorieVacanta cv1, CalatorieVacanta cv2) 
	{
		if(cv1.getCheltuieli()<cv2.getCheltuieli())
			return -1;
		if(cv1.getCheltuieli()>cv2.getCheltuieli())
			return 1;
		return 0;
	}
	
	
	
	
}
