package clase;

import java.io.Serializable;

public abstract class Tren implements Serializable
{

	private static final long serialVersionUID = -3253989498124314269L;
	private String serie;
	private float tonaj;
	private final String marca;
	
	public Tren(String serie, float tonaj, String marca) {
		this.serie = serie;
		this.tonaj = tonaj;
		this.marca = marca;
	}

	public String getSerie() {
		return serie;
	}

	public float getTonaj() {
		return tonaj;
	}

	public String getMarca() {
		return marca;
	}
	
	
	public abstract double getCapacitate();
	
	
	
	
}
