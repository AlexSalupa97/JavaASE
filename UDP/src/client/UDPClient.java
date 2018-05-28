package ro.ase.acs.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public static void main(String[] args) {
		byte[] buffer = null;
		Scanner scanner = new Scanner(System.in);
		
		try(DatagramSocket client =
				new DatagramSocket()) {
			while(true) {
				InetAddress serverAddress = 
						InetAddress.getByName("localhost");
				int serverPort = 6700;
				System.out.print("Mesaj: ");
				String mesaj = scanner.nextLine();
				buffer = mesaj.getBytes();
				
				DatagramPacket packet = 
						new DatagramPacket(buffer,
								buffer.length,
								serverAddress,
								serverPort);
				client.send(packet);
				
				buffer = new byte[256];
				DatagramPacket newPacket =
						new DatagramPacket(buffer,
								buffer.length);
				client.receive(newPacket);
				String mesajDeLaServer = 
						new String(newPacket.getData());
				System.out.println("Mesaj de la server: " +
						mesajDeLaServer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}
}
