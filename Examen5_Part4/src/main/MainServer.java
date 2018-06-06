package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import clase.Avion;
import clase.AvionPasageri;
import tcpServer.TCPServerThread;

public class MainServer 
{

	public static void main(String[] args) 
	{
		
		try(ServerSocket serverSocket=new ServerSocket(6700);Scanner in=new Scanner(System.in))
		{
			
			System.out.println("Server ready on 6700!");
			while(true)
			{
				Socket socket = serverSocket.accept();
				TCPServerThread t = new TCPServerThread(socket);
				t.start();
				
				System.out.println("x -> exit");
				String x=in.next();
				if(x.equals("x"))
					break;
			
					
			}
			System.out.println(TCPServerThread.listaAvioane);
			
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server inchis!");
		
		try 
		{
			Class.forName("org.sqlite.JDBC");
			System.out.println("Class.forName!");
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection con=null;
		
		try 
		{
			con=DriverManager.getConnection("jdbc:sqlite:database.db");
			System.out.println("Conexiune!");
			con.setAutoCommit(false);
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement st1=null;
		try {
			st1=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String createTableAP="create table avioane_pasageri (serie text primary key not null, tonaj float, marca text, nr_locuri int)";
		
		String createTableCNP="create table cnp_uri (cnp text primary key not null)";
		
		String createTableAPCNP="create table ap_cnp (ap_cnp int primary key not null, serie text, cnp text,FOREIGN KEY(serie) REFERENCES avioane_pasageri(serie),FOREIGN KEY(cnp) REFERENCES cnp_uri(cnp))";
		
		
		try {
			File f=new File("database.db");
			if(!f.exists())
			{
				st1.executeUpdate(createTableAP);
				st1.executeUpdate(createTableCNP);
				st1.executeUpdate(createTableAPCNP);
			}
			//st1.close();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int x=1;
		String sql="insert into avioane_pasageri values(?,?,?,?)";
		String sql1="insert into ap_cnp values(?,?,?)";
		try 
		{
			st1.executeUpdate("delete from avioane_pasageri");
			st1.executeUpdate("delete from ap_cnp");
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1=con.prepareStatement(sql1);
			Iterator<Avion> it=TCPServerThread.listaAvioane.iterator();
			for(;it.hasNext();)
			{
				AvionPasageri ap = (AvionPasageri) it.next();
				ps.setString(1, ap.getSerie());
				ps.setFloat(2, ap.getTonaj());
				ps.setString(3, ap.getMARCA());
				ps.setInt(4, ap.getNrLocuri());
				ps.executeUpdate();
				System.out.println("Insert avioane_pasageri!");
				
				Iterator<String> itS=ap.getCnpPasageri().iterator();
				for(;itS.hasNext();)
				{
				ps1.setInt(1,x );
				x++;
				ps1.setString(2, ap.getSerie());
				ps1.setString(3, itS.next());
				ps1.executeUpdate();
				System.out.println("Insert ap_cnp!");
				}
			}
			ps.close();
			ps1.close();
			con.commit();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Avion> listaBD=new ArrayList<>();
		
		try
		{
			ResultSet rs=st1.executeQuery("select * from avioane_pasageri");
			while(rs.next())
			{
				String serie= rs.getString(1);
				float tonaj=rs.getFloat(2);
				String marca=rs.getString(3);
				int nr=rs.getInt(4);
				List<String> cnp=new Vector<>();
				ResultSet rs1=st1.executeQuery("select cnp from ap_cnp where serie="+serie);
				while(rs1.next())
					cnp.add(rs1.getString(1));
				AvionPasageri ap=new AvionPasageri(serie, tonaj, marca, nr, cnp);
				listaBD.add(ap);
				rs1.close();	
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject json=new JSONObject();
		AvionPasageri ap=(AvionPasageri) listaBD.get(0);
		
		try 
		{
			json.put("serie", ap.getSerie());
			json.put("tonaj", ap.getTonaj());
			json.put("marca", ap.getMARCA());
			json.put("nr_locuri", ap.getNrLocuri());
			JSONArray array=new JSONArray();
			for(String s:ap.getCnpPasageri())
				array.put(s);
			json.put("cnp-uri", array);
			FileWriter fw=new FileWriter("avioanePasageri.json");
			fw.write(json.toString());
			fw.close();
		} 
		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try 
		{
			JAXBContext context=JAXBContext.newInstance(AvionPasageri.class);
			Marshaller m=context.createMarshaller();
			m.marshal(ap, new File("avioanePasageri.xml"));
		}
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
