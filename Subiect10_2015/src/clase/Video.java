package clase;

public class Video extends Media
{
	private int rezolutie;

	public Video(String denumire, float lungime, int rezolutie) throws Exception
	{
		super(denumire, lungime);
		this.rezolutie = rezolutie;
		if(denumire==null)
			throw new Exception("Denumire inexistenta!");
	}

	@Override
	public void returneazaPlayer() 
	{
		System.out.println("Player video -> Fisier: "+getDenumire()+" | Lungime: "+getLungime()+" sec | Rezolutie: "+this.rezolutie+" p");
		
	}
	
	
	
}
