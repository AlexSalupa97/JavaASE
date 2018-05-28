package ro.ase.acs.udp.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPServer {
	
	public static void main(String[] args) {
		final short port = 6700;
		byte[] bufferIntrare = null;
		byte[] bufferIesire = null;
		Scanner scanner = new Scanner(System.in);
		
		try(DatagramSocket server = 
				new DatagramSocket(port)) {
			System.out.println("Server started on port: " + port);
			while(true) {
				bufferIntrare = new byte[256];
				DatagramPacket packet = 
						new DatagramPacket(bufferIntrare,
								bufferIntrare.length);
				server.receive(packet);
				String mesaj = new String(bufferIntrare);
				System.out.println("Mesaj de la client: " + 
						mesaj);
				System.out.print("Mesaj: ");
				String msg = scanner.nextLine();
				bufferIesire = msg.getBytes();
				InetAddress clientAddress = packet.getAddress();
				int clientPort = packet.getPort();
				
				DatagramPacket newPacket =
						new DatagramPacket(bufferIesire,
								bufferIesire.length,
								clientAddress,
								clientPort);
				server.send(newPacket);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}
}
