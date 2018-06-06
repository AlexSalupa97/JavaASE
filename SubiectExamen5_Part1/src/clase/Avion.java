package clase;

import java.io.Serializable;

public abstract class Avion implements Serializable 
{

	private static final long serialVersionUID = 6666181943583639991L;
	private String serie;
	private float tonaj;
	private final String MARCA;
	
	
	public Avion(String serie, float tonaj, String mARCA) {
		this.serie = serie;
		this.tonaj = tonaj;
		MARCA = mARCA;
	}


	public String getSerie() {
		return serie;
	}


	public float getTonaj() {
		return tonaj;
	}


	public String getMARCA() {
		return MARCA;
	}
	
	public abstract double getCapacitate();
	
	

}
