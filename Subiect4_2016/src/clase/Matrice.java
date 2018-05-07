package clase;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Matrice implements Cloneable,Comparable<Matrice>
{
	private int linii;
	private int coloane;
	private Object[][] matrice;
	
	public Matrice()
	{
	}

	public Matrice(int linii, int coloane, Object[][] matrice) 
	{
		this.linii = linii;
		this.coloane = coloane;
		this.matrice = new Object[linii][coloane];
		for(int i=0;i<matrice.length;i++)
			this.matrice[i]=Arrays.copyOf(matrice[i], matrice[i].length);
	}

	public int getLinii() {
		return linii;
	}

	public void setLinii(int linii) {
		this.linii = linii;
	}

	public int getColoane() {
		return coloane;
	}

	public void setColoane(int coloane) {
		this.coloane = coloane;
	}

	public Object[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(Object[][] matrice) {
		this.matrice = matrice;
	}
	
	public Object minim()
	{
		Object min=matrice[0][0];
		for(int i=0;i<matrice.length;i++)
			for(int j=0;j<matrice[i].length;j++)
				if((double)min>(double)matrice[i][j])
					min=matrice[i][j];
		return min;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Matrice m=(Matrice)super.clone();
		this.matrice=new Object[m.linii][m.coloane];
		for(int i=0;i<matrice.length;i++)
			this.matrice[i]=Arrays.copyOf(m.matrice[i], m.matrice[i].length);
		return m;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if(!(obj instanceof Matrice))
			return false;
		Matrice m=(Matrice)obj;
		if(m.matrice==this.matrice)
			return true;
		return false;
	}

	@Override
	public int compareTo(Matrice o) 
	{
		if((double)this.minim()<(double)o.minim())
			return -1;
		if((double)this.minim()==(double)o.minim())
			return 0;
		return 1;
	}
	
	
	
}
