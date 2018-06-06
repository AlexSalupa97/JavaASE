package clase;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class AvionCargo extends Avion implements Serializable,Cloneable
{

	private static final long serialVersionUID = -7165892099874946165L;
	private float capacitateTransportKg;
	private List<String> serieMarfuri=new Vector<>();
	
	public AvionCargo(String serie, float tonaj, String mARCA, float capacitateTransportKg, List<String> serieMarfuri) throws Exception {
		super(serie, tonaj, mARCA);
		this.capacitateTransportKg = capacitateTransportKg;
		this.serieMarfuri = serieMarfuri;
		if(tonaj<0)
			throw new Exception("Tonaj negativ");
	}

	@Override
	public double getCapacitate() {
		return capacitateTransportKg;
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

	public AvionCargo() {
		super(null,0,null);
		this.capacitateTransportKg = 0;
		this.serieMarfuri = null;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AvionCargo ap=(AvionCargo)super.clone();
		List<String> lista=new Vector<>(this.serieMarfuri);
		ap.serieMarfuri=lista;
		return ap;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof AvionCargo))
			return false;
		else
		{
			AvionCargo ap=(AvionCargo)obj;
			if(this.capacitateTransportKg==ap.capacitateTransportKg)
				return true;
		}
		return false;
	}
	
	
	
	
	
}
