package clase;

import java.io.Serializable;

public class Masina implements Serializable
{

	private static final long serialVersionUID = 1986L;
	private Motor motor;
	private String producator;
	
	
	public Motor getMotor() {
		return motor;
	}
	public void setMotor(Motor motor) {
		this.motor = motor;
	}
	public String getProducator() {
		return producator;
	}
	public void setProducator(String producator) {
		this.producator = producator;
	}
	
	
	
}
