package clase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class TCPServerThread extends Thread 
{
	private Socket client;
	public static List<Tren> listaTrenuri;
	
	public TCPServerThread(Socket client) {
		
		this.client = client;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		try 
		{
			ObjectInputStream is=new ObjectInputStream(client.getInputStream());
			listaTrenuri=(List<Tren>)is.readObject();
			client.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
