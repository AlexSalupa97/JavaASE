package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import clase.Student;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		List<Student> lista=new ArrayList<>();
		
		for(int i=0;i<10;i++)
			lista.add(new Student(Integer.toString(i+1),i+1,i+1));

		List<Student> listaStream=lista.stream().filter(x->x.getMedie()>5).collect(Collectors.toList());
		
		//lista.parallelStream().map(Student::getMedie).forEach(System.out::println);
	
		//new Random().ints(10,20).limit(10).forEach(System.out::println);
	}

}
