package clase;

public class TitluCalatorieUrban extends TitluCalatorie implements Cloneable
{
	private String denumireOperatorUrban;
	
	public TitluCalatorieUrban(int id, String denumire, float idLinie, String dataStart, String dataStop,
			String denumireOperatorUrban) throws Exception 
	{
		super(id, denumire, idLinie, dataStart, dataStop);
		this.denumireOperatorUrban = denumireOperatorUrban;
		if(idLinie<0)
			throw new Exception("ID negativ");
	}

	
	@Override
	public String getIdZona() 
	{
		return this.getIdLinie()+"/"+this.denumireOperatorUrban;
	}


	@Override
	protected Object clone() throws CloneNotSupportedException {
		TitluCalatorieUrban t=(TitluCalatorieUrban)super.clone();
		return t;
	}


	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof TitluCalatorieUrban))
			return false;
		TitluCalatorieUrban aux=(TitluCalatorieUrban)obj;
		if(this.denumireOperatorUrban==aux.denumireOperatorUrban)
			return true;
		return false;
	}
	
	

}
