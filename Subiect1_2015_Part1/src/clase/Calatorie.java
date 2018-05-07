package clase;

import java.io.Serializable;

public abstract class Calatorie  implements Serializable
{
	private static final long serialVersionUID = 1L;
	String destinatie;
	int nr_zile;
	String mod_transport;
	
	public Calatorie()
	{
		
	}
	
	public Calatorie(String destinatie, int nr_zile, String mod_transport) 
	{
		this.destinatie = destinatie;
		this.nr_zile = nr_zile;
		this.mod_transport = mod_transport;
	}

	public void setDestinatie(String destinatie) {
		this.destinatie = destinatie;
	}

	public void setNr_zile(int nr_zile) {
		this.nr_zile = nr_zile;
	}

	public void setMod_transport(String mod_transport) {
		this.mod_transport = mod_transport;
	}
	
	public abstract float getCheltuieli();
	
	
}
