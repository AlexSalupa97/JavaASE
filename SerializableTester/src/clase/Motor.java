package clase;

import java.io.Serializable;


public class Motor implements Serializable
{
	private int putere;
	private double consum;
	private static final long serialVersionUID=1947L;
	public enum TipCombustibil{benzina,motorina,hybrid};
	private TipCombustibil combustibil;
	
	public int getPutere() {
		return putere;
	}
	public void setPutere(int putere) {
		this.putere = putere;
	}
	public double getConsum() {
		return consum;
	}
	public void setConsum(double consum) {
		this.consum = consum;
	}
	public TipCombustibil getCombustibil() {
		return combustibil;
	}
	public void setCombustibil(TipCombustibil combustibil) {
		this.combustibil = combustibil;
	}
	
}
