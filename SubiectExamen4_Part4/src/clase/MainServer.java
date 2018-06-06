package clase;

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
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainServer {

	public static void main(String[] args)
	{
	
		try(ServerSocket server=new ServerSocket(6700);Scanner in=new Scanner(System.in))
		{
			System.out.println("Server up");
			while(true)
			{
				Socket client=server.accept();
				TCPServerThread t=new TCPServerThread(client);
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
		System.out.println("Server down");
		System.out.println(TCPServerThread.listaTrenuri.size());

		
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
		
		
		String tableTP="create table tp (serie text primary key not null, tonaj float, marca text, nr_locuri int)";
		String tableCNP="create table cnp (cnp text primary key not null)";
		String tableTPCNP="create table tpcnp (tpcnp int primary key not null, serie text, cnp text, foreign key(serie) references tp(serie), foreign key(cnp) references cnp(cnp))";
	
		try {
			st.executeUpdate(tableTP);
			st.executeUpdate(tableCNP);
			st.executeUpdate(tableTPCNP);
		} catch (SQLException e) {
			System.out.println("Tabelele exista");
		}
		
		String sql="insert into tp values(?,?,?,?)";
		String sql1="insert into tpcnp values(?,?,?)";
		int x=1;
		
		try {
			st.executeUpdate("delete from tp");
			st.executeUpdate("delete from tpcnp");
			PreparedStatement ps=con.prepareStatement(sql);
			PreparedStatement ps1=con.prepareStatement(sql1);
			
			for(Tren t: TCPServerThread.listaTrenuri )
			{
				TrenPasageri tp=(TrenPasageri)t;
				ps.setString(1, tp.getSerie());
				ps.setFloat(2, tp.getTonaj());
				ps.setString(3, tp.getMarca());
				ps.setInt(4, tp.getNrLocuri());
				
				ps.executeUpdate();
				
				for(String s:tp.getCnpPasageri())
				{
					ps1.setInt(1, x);
					x++;
					ps1.setString(2, tp.getSerie());
					ps1.setString(3, s);
					ps1.executeUpdate();
				}
			}
			System.out.println("insert");
			ps1.close();
			ps.close();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Tren> listaBD=new ArrayList<>();
		try {
			ResultSet rs=st.executeQuery("select * from tp");
			ResultSet rs1=null;
			while(rs.next())
			{
				String serie=rs.getString(1);
				float tonaj=rs.getFloat(2);
				String marca=rs.getString(3);
				int nrLocuri=rs.getInt(4);
				rs1=st1.executeQuery("select cnp from tpcnp where serie="+serie);
				List<String> cnp=new Vector<>();
				while(rs1.next())
					cnp.add(rs1.getString(1));
				
				TrenPasageri tp=new TrenPasageri(serie, tonaj, marca, nrLocuri, cnp);
				listaBD.add(tp);
			}
			rs1.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(listaBD.size());
		
		
		try (FileWriter fw=new FileWriter("tp.json"))
		{
			JSONObject json=new JSONObject();
			for(Tren t:listaBD)
			{
				TrenPasageri tp=(TrenPasageri)t;
				json.put("serie", tp.getSerie());
				json.put("tonaj", tp.getTonaj());
				json.put("marca", tp.getMarca());
				json.put("nr_locuri", tp.getNrLocuri());
				
				JSONArray array=new JSONArray();
				for(String s:tp.getCnpPasageri())
					array.put(s);
				
				json.put("cnp", array);
				fw.write(json.toString());
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
