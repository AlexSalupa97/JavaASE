package tcpServer;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import clase.Avion;

public class TCPServerThread extends Thread
{
	private Socket clientSocket;
	public static List<Avion> listaAvioane=new ArrayList<>();
	
	public TCPServerThread(Socket s)
	{
		this.clientSocket=s;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void run() 
	{
		try
		{
			
			ObjectInputStream objIS=new ObjectInputStream(clientSocket.getInputStream());
			listaAvioane=(List<Avion>)objIS.readObject();
			clientSocket.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

}
