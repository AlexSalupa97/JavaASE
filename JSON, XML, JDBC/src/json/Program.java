package ro.ase.acs.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ro.ase.acs.clasa.Student;

public class Program {

	public static void main(String[] args) {
		Student student = new Student();
		student.setNume("Gigel Ionescu");
		student.setNrMatricol(123);
		student.setLocBugetat(true);
		int[] note = { 10, 9, 8 };
		student.setNote(note);
		
		JSONObject json = new JSONObject();
		try {
			json.put("nume", student.getNume());
			json.put("matricol", student.getNrMatricol());
			json.put("buget", student.isLocBugetat());
			JSONArray array = new JSONArray();
			for(int x : student.getNote()) {
				array.put(x);
			}
			json.put("note", array);
			FileWriter fw = new FileWriter("student.json");
			fw.write(json.toString());
			fw.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try(FileReader fr = new FileReader("student.json")) {
			BufferedReader reader = new BufferedReader(fr);
			String linie = null;
			StringBuilder sb = new StringBuilder();
			while((linie = reader.readLine()) != null) {
				sb.append(linie);
				sb.append("\r\n");
			}
			reader.close();
			System.out.println("JSON citit din fisier: ");
			System.out.println(sb.toString());
			
			JSONObject jsonObject = 
					new JSONObject(sb.toString());
			Student s = new Student();
			s.setNume(jsonObject.getString("nume"));
			s.setNrMatricol(jsonObject.getInt("matricol"));
			s.setLocBugetat(jsonObject.getBoolean("buget"));
			JSONArray jsonArray = 
					jsonObject.getJSONArray("note");
			int[] v = new int[jsonArray.length()];
			for(int i=0; i<jsonArray.length(); i++) {
				v[i] = jsonArray.getInt(i);
			}
			s.setNote(v);
			System.out.println(s);
		}
		catch(IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
