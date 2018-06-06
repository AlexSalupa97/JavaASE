package clase;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class AvionCargo extends Avion implements Cloneable, Serializable
{
	
	
	private static final long serialVersionUID = 2L;
	private float capacitateTransportKg;
	private List<String> serieMarfuri=new Vector<>();

	public AvionCargo(String serie, float tonaj, String MARCA, float capacitateTransportKg, List<String> serieMarfuri) throws Exception {
		super(serie, tonaj, MARCA);
		this.capacitateTransportKg = capacitateTransportKg;
		this.serieMarfuri = serieMarfuri;
		if(tonaj<0)
			throw new Exception("Tonaj negativ");	
	}

	@Override
	public double getCapacitate() {
		return this.capacitateTransportKg;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AvionCargo ac=(AvionCargo)super.clone();
		List<String> serie=new Vector<String>(this.serieMarfuri);
		ac.serieMarfuri=serie;
		return ac;
	}

	public float getCapacitateTransportKg() {
		return capacitateTransportKg;
	}

	public void setCapacitateTransportKg(float capacitateTransportKg) {
		this.capacitateTransportKg = capacitateTransportKg;
	}

	public List<String> getSerieMarfuri() {
		return serieMarfuri;
	}

	public void setSerieMarfuri(List<String> serieMarfuri) {
		this.serieMarfuri = serieMarfuri;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if(this==obj)
			return true;
		if(!(obj instanceof AvionCargo))
			return false;
		else
		{
			AvionCargo ac=(AvionCargo)obj;
			if(this.capacitateTransportKg==ac.capacitateTransportKg)
				return true;
		}
		return false;
		}
}
