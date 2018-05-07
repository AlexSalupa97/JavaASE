package clase;

public abstract class Media 
{
	private String denumire;
	private float lungime;
	private final int semnaturaUnica=10;
	
	
	public Media(String denumire, float lungime) 
	{
		this.denumire = denumire;
		this.lungime = lungime;
	}


	public String getDenumire() {
		return denumire;
	}


	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}


	public float getLungime() {
		return lungime;
	}


	public void setLungime(float lungime) {
		this.lungime = lungime;
	}


	public int getSemnaturaUnica() {
		return semnaturaUnica;
	}
	
	public abstract void returneazaPlayer();
	
	
}
