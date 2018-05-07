package clase;

public class Audio extends Media 
{
	private int calitate;

	public Audio(String denumire, float lungime, int calitate) throws Exception
	{
		super(denumire, lungime);
		this.calitate = calitate;
		if(denumire==null)
			throw new Exception("Denumire inexistenta!");
	}

	@Override
	public void returneazaPlayer() 
	{
		System.out.println("Player audio -> Fisier: "+getDenumire()+" | Lungime: "+getLungime()+" sec | Calitate: "+this.calitate+" MHz");
		
	}
	
	
	

}
