package ro.ase.acs.tcp.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	public static void main(String[] args) {
		final String adresaServer = "localhost";
		final short portServer = 6700;
		Scanner scanner = new Scanner(System.in);
		
		Runnable r = () -> {
			System.out.print("Nume: ");
			String nume = scanner.nextLine();
			System.out.print("Mesaj: ");
			String mesaj = scanner.nextLine();
			while(!"exit".equals(mesaj)) {
				try(Socket client = 
						new Socket(adresaServer, portServer)) {
					OutputStream os = client.getOutputStream();
					DataOutputStream dataOutput =
							new DataOutputStream(os);

					dataOutput.writeUTF(nume + ": " + mesaj);
					dataOutput.close();
					os.close();
					System.out.print("Mesaj: ");
					mesaj = scanner.nextLine();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread t = new Thread(r);
		t.start();
		//...
		try {
			t.join();
			scanner.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
