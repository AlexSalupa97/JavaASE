package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import clase.Avion;

public class ServerThread extends Thread
{
	private Socket client;
	public static List<Avion> listaAvioane=new ArrayList<>();
	
	public ServerThread(Socket client) 
	{
		this.client=client;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream os=new ObjectInputStream(client.getInputStream());
			listaAvioane=(List<Avion>)os.readObject();
			client.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
