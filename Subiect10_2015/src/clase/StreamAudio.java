package clase;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class StreamAudio extends Audio implements Streamable,Serializable
{
	
	private static final long serialVersionUID = 1L;

	public StreamAudio(String denumire, float lungime, int calitate) throws Exception {
		super(denumire, lungime, calitate);
	}


	private int[] stream;

	public void setStream(int[] stream) {
		this.stream = stream;
	}

	
	@Override
	public void citesteStreamFisier(String numeFisier) 
	{
		int nrElem=0;
		int[] vector=null;;
		try(DataInputStream reader=new DataInputStream(new FileInputStream(numeFisier)))
		{
			nrElem=reader.available();
			vector=new int[nrElem];
			for(int i=0;i<nrElem;i++)
				vector[i]=reader.readByte();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		this.setStream(vector);
	}


	@Override
	public void returneazaPlayer() 
	{
		StringBuilder sb=new StringBuilder();
		sb.append("A~");
		
		int nr=(int)getLungime()*1000/500;
		for(int i=0;i<nr;i++)
			sb.append(getStream(i));
		
		super.returneazaPlayer();
		System.out.println(sb);
			
	}
	
	
	public int getStream(int i)
	{
		return stream[i];
	}
	
}
