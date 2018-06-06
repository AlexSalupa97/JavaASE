package clase;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;



public abstract class Avion implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String serie;
	private float tonaj;
	private final String MARCA;
	
	public Avion(String serie, float tonaj, String MARCA)
	{
		this.serie = serie;
		this.tonaj = tonaj;
		this.MARCA = MARCA;
	}

	@XmlElement
	public String getSerie() {
		return serie;
	}

	@XmlElement
	public float getTonaj() {
		return tonaj;
	}

	
	@XmlElement
	public String getMARCA() {
		return MARCA;
	}
	
	public abstract double getCapacitate();
	

}
