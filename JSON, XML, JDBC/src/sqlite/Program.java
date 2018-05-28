package ro.ase.acs.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ro.ase.acs.clasa.Student;

public class Program {

	public static void main(String[] args) {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:database.db");
			c.setAutoCommit(false);
			
			creareTabela(c);
			inserareDate(c);
			selectStudenti(c);
			
			c.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void creareTabela(Connection c) 
			throws SQLException {
		Statement stmt = c.createStatement();
		
		String sqlDropTable = "drop table if exists studenti";
		String sqlCreateTable = "create table studenti " +
				"(id INT PRIMARY KEY NOT NULL," +
				"nume TEXT NOT NULL, matricol INT, buget BOOLEAN)";
		
		stmt.executeUpdate(sqlDropTable);
		stmt.executeUpdate(sqlCreateTable);
		
		stmt.close();
		c.commit();
	}
	
	public static void inserareDate(Connection c) 
			throws SQLException {
		Statement stmt = null; String sql = null;
		
		stmt = c.createStatement();
		sql = "insert into studenti(id, nume, matricol, buget) values"
				+ "(1, 'Ionel', 456, 1)";
		stmt.executeUpdate(sql);
		stmt.close();
		
		PreparedStatement ps = c.prepareStatement("insert into studenti(id, nume, matricol, buget) values"
				+ "(?, ?, ?, ?)");
		
		ps.setInt(1, 2);
		ps.setString(2, "Gigel");
		ps.setInt(3, 789);
		ps.setBoolean(4, false);
		
		ps.executeUpdate();
		
		ps.close();
		
		c.commit();
	}
	
	public static void selectStudenti(Connection c) 
			throws SQLException {
		Statement stmt = c.createStatement();
		String sqlSel = "select * from studenti;";
		
		
		ResultSet rs = stmt.executeQuery(sqlSel);
		while (rs.next()) {
			Student s = new Student();
			s.setNume(rs.getString("nume"));
			s.setNrMatricol(rs.getInt("matricol"));
			s.setLocBugetat(rs.getBoolean("buget"));
			System.out.println(s);
		}
		
		rs.close();
		stmt.close();
	}

}
