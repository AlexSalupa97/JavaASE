package clase;

import java.io.Serializable;

public class Autovehicul extends Vehicul implements Driveable,Serializable
{
	private static final long serialVersionUID = 1986L;
	private int caiPutere;

	public Autovehicul(String marca, float greutate, int nr_roti, int caiPutere) throws Exception 
	{
		super(marca, greutate, nr_roti);
		this.caiPutere = caiPutere;
		if(caiPutere<0)
			throw new Exception("CaiPutere < 0");
	}

	public int getCaiPutere() {
		return caiPutere;
	}

	public void setCaiPutere(int caiPutere) {
		this.caiPutere = caiPutere;
	}

	@Override
	public boolean esteInMiscare() 
	{
		if(this.getViteza()>0.0)
			return true;
		return false;
	}

	@Override
	public double getViteza() 
	{
		
		return caiPutere*0.5;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException 
	{
		Autovehicul a=(Autovehicul)super.clone();
		return a;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if(this==obj)
			return true;
		if(!(obj instanceof Autovehicul))
			return false;
		
		Autovehicul a=(Autovehicul)obj;
		if(this.getMarca()==a.getMarca()&&this.getCaiPutere()==a.getCaiPutere())
			return true;
		return false;
					
	}
	
	
	
	
}
