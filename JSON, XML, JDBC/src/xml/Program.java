package ro.ase.acs.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ro.ase.acs.clasa.Student;

public class Program {

	public static void main(String[] args) {
		Student student = new Student();
		student.setNume("Gigel Ionescu");
		student.setNrMatricol(123);
		student.setLocBugetat(true);
		int[] note = { 10, 9, 8 };
		student.setNote(note);
		
		try {
			JAXBContext context = 
					JAXBContext.newInstance(Student.class);
			Marshaller m = context.createMarshaller();
			m.marshal(student, new File("student.xml"));
			
			Unmarshaller u = context.createUnmarshaller();
			Student s = 
				(Student)u.unmarshal(new File("student.xml"));
			System.out.println("Obiect din XML: ");
			System.out.println(s);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
