package clase;

public class TitluCalatorieMetropolitan extends TitluCalatorie
{

	private String denumireOperatorMetropolitan;
	
	public TitluCalatorieMetropolitan(int id, String denumire, float idLinie, String dataStart, String dataStop,
			String denumireOperatorMetropolitan) throws Exception 
	{
		super(id, denumire, idLinie, dataStart, dataStop);
		this.denumireOperatorMetropolitan = denumireOperatorMetropolitan;
		if(idLinie<0)
			throw new Exception("ID negativ");
	}

	@Override
	public String getIdZona() {
		return this.getIdLinie()+"/"+this.denumireOperatorMetropolitan;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		TitluCalatorieMetropolitan t=(TitluCalatorieMetropolitan)super.clone();
		return t;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof TitluCalatorieMetropolitan))
			return false;
		TitluCalatorieMetropolitan aux=(TitluCalatorieMetropolitan)obj;
		if(this.denumireOperatorMetropolitan==aux.denumireOperatorMetropolitan)
			return true;
		return false;
	}
	
	
	
	
}
