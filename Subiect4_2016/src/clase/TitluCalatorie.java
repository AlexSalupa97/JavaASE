package clase;

public abstract class TitluCalatorie 
{
	private int id;
	private String denumire;
	private float idLinie;
	private String dataStart;
	private String dataStop;
	
	public TitluCalatorie(int id, String denumire, float idLinie, String dataStart, String dataStop) 
	{
		this.id = id;
		this.denumire = denumire;
		this.idLinie = idLinie;
		this.dataStart = dataStart;
		this.dataStop = dataStop;
	}

	public int getId() {
		return id;
	}

	public String getDenumire() {
		return denumire;
	}

	public float getIdLinie() {
		return idLinie;
	}

	public String getDataStart() {
		return dataStart;
	}

	public String getDataStop() {
		return dataStop;
	}
	
	public abstract String getIdZona();
	
}
