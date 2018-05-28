package ro.ase.acs.tcp.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {
	final short port = 6700;
	
	public static void main(String[] args) {
		TCPServer server = new TCPServer();
		server.start();
	}
	
	@Override
	public void run() {
		try(ServerSocket serverSocket =
				new ServerSocket(port)) {
				System.out.println("Server started on port: " + port);
			while(true) {
				Socket server = serverSocket.accept();
				InputStream is = server.getInputStream();
				DataInputStream dataInput =
						new DataInputStream(is);
				String mesaj = dataInput.readUTF();
				System.out.println(mesaj);
				dataInput.close();
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}
