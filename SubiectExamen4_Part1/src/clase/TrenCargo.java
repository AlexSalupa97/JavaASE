package clase;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class TrenCargo extends Tren implements Serializable, Cloneable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7654639889111898770L;
	private float capacitateTransportKg;
	private List<String> serieMarfuri=new Vector<>();
	
	public TrenCargo(String serie, float tonaj, String marca, float capacitateTransportKg, List<String> serieMarfuri) throws Exception {
		super(serie, tonaj, marca);
		this.capacitateTransportKg = capacitateTransportKg;
		this.serieMarfuri = serieMarfuri;
		if(tonaj<0)
			throw new Exception("meh");
	}

	public float getCapacitateTransportKg() {
		return capacitateTransportKg;
	}

	public List<String> getSerieMarfuri() {
		return serieMarfuri;
	}

	@Override
	public double getCapacitate() {
		// TODO Auto-generated method stub
		return capacitateTransportKg;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		TrenCargo tp=(TrenCargo)super.clone();
		List<String> lista=new Vector<>(this.serieMarfuri);
		tp.serieMarfuri=lista;
		return tp;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(!(obj instanceof TrenCargo))
			return false;
		else
		{
			TrenCargo tp=(TrenCargo)obj;
			if(this.capacitateTransportKg==tp.capacitateTransportKg)
				return true;
			
		}
		return false;
	}
	
	
	
	
	
	

}
