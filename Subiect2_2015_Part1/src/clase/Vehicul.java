package clase;

import java.io.Serializable;

public abstract class Vehicul implements Serializable
{
	private static final long serialVersionUID = 1947L;
	private String marca;
	private float greutate;
	private int nr_roti;
	
	public Vehicul(String marca, float greutate, int nr_roti) {
		this.marca = marca;
		this.greutate = greutate;
		this.nr_roti = nr_roti;
	}

	public String getMarca() {
		return marca;
	}

	public float getGreutate() {
		return greutate;
	}

	public int getNr_roti() {
		return nr_roti;
	}
	
	public abstract double getViteza();
	
	

}
