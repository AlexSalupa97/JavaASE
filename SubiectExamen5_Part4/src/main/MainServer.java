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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import clase.Avion;
import clase.AvionPasageri;

public class MainServer
{

	public static void main(String[] args) 
	{
		try(ServerSocket server=new ServerSocket(6700);Scanner in= new Scanner(System.in)) 
		{
			System.out.println("Server on");
			while(true)
			{
			Socket client=server.accept();
			ServerThread t=new ServerThread(client);
			t.start();
			
			System.out.println("x -> exit");
			if(in.next().equals("x"))
				break;
			
			
			}
			
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Server off");

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection con=null;
		
		try {
			con=DriverManager.getConnection("jdbc:sqlite:database.db");
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement st=null;
		Statement st1=null;
		try {
			st=con.createStatement();
			st1=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String tableAP="create table avioane_pasageri (serie text primary key not null, tonaj float, marca text, nr_locuri int)";
		String tableCNP="create table cnp_uri (cnp text primary key not null)";
		String tableAPCNP="create table ap_cnp (ap_cnp int primary key not null, serie text, cnp text, foreign key(serie) references avioane_pasageri(serie), foreign key(cnp) references cnp_uri(cnp))";
		
		
		
		{
			
			try 
			{
				st.executeUpdate(tableAP);
				st.executeUpdate(tableCNP);
				st.executeUpdate(tableAPCNP);
				con.commit();
			} 
			catch (SQLException e) {
				System.out.println("Tabelele exista!");
			}
		}
		
		String sql1="insert into avioane_pasageri values(?,?,?,?)";
		String sql2="insert into ap_cnp values(?,?,?)";
		int x=1;
		
		try 
		{
			st.executeUpdate("delete from avioane_pasageri");
			st.executeUpdate("delete from ap_cnp");
			PreparedStatement ps1=con.prepareStatement(sql1);
			PreparedStatement ps2=con.prepareStatement(sql2);
			Iterator<Avion> it=ServerThread.listaAvioane.iterator();
			for(;it.hasNext();)
			{
				AvionPasageri ap=(AvionPasageri)it.next();
				ps1.setString(1, ap.getSerie());
				ps1.setFloat(2, ap.getTonaj());
				ps1.setString(3, ap.getMARCA());
				ps1.setInt(4, ap.getNrLocuri());
				
				ps1.executeUpdate();
				
				Iterator<String> its=ap.getCnpPasageri().iterator();
				for(;its.hasNext();)
				{
					ps2.setInt(1, x);
					x++;
					ps2.setString(2, ap.getSerie());
					ps2.setString(3, its.next());
					ps2.executeUpdate();
					
				}
			}
			
			ps1.close();
			ps2.close();
			con.commit();
			System.out.println("Insert done");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<Avion> listaBD=new ArrayList<>();
		try 
		{
			ResultSet rs=st.executeQuery("select * from avioane_pasageri");
			ResultSet rs1=null;
			while(rs.next())
			{
				String serie=rs.getString(1);
				float tonaj=rs.getFloat(2);
				String marca=rs.getString(3);
				int nrLocuri=rs.getInt(4);
				
				rs1=st1.executeQuery("select cnp from ap_cnp where serie="+serie);
				List<String> cnp=new Vector<>();
				while(rs1.next())
				{
					cnp.add(rs1.getString(1));
				}
				AvionPasageri ap=new AvionPasageri(serie, tonaj, marca, nrLocuri, cnp);
				listaBD.add(ap);
			}
			rs1.close();
			rs.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AvionPasageri apt=(AvionPasageri) listaBD.get(0);
		System.out.println(apt.getCnpPasageri());
		
		JSONObject json=new JSONObject();
		
		Iterator<Avion> it=listaBD.iterator();
		

		try(FileWriter fw = new FileWriter("ap.json")) 
		{
			;
		
			for(;it.hasNext();)
			{
				AvionPasageri ap=(AvionPasageri)it.next();
				try 
				{
					json.put("serie", ap.getSerie());
					json.put("tonaj", ap.getTonaj());
					json.put("marca", ap.getMARCA());
					json.put("nr_locuri", ap.getNrLocuri());
					JSONArray array=new JSONArray();
					Iterator<String> its=ap.getCnpPasageri().iterator();
					for(;its.hasNext();)
						array.put(its.next());
					json.put("cnp", array);

					fw.write(json.toString());
				} 
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
