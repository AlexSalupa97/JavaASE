package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import clase.Student;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) 
	{
//		////////////////////////////////////////////////////////
//		
//		List<Student> VectorSt=new Vector<Student>();
//		List<Student> ArrayListSt=new ArrayList<Student>();
//		List<Student> LinkedListSt=new LinkedList<Student>();
//		
//		for(int i=0;i<5;i++)
//			ArrayListSt.add(new Student(Integer.toString(i+1),i+1,i+1));
//		
//		System.out.println("Array list:");
//		
//		Iterator<Student> itSt=ArrayListSt.iterator();
//		
//		for(;itSt.hasNext();)
//			System.out.println(itSt.next().toString());
//		
//		
//		///////////////////////////////////////////////////////
//		
//		Map<Student,Student> TreeMapSt=new TreeMap<>();
//		Map<Integer,Student> HashMapSt=new HashMap<>();
//		Map<Student,Student> LinkedHashMapSt=new LinkedHashMap<>();
//		
//		Student value;
//		
//		for(int i=1;i<=5;i++)
//		{
//			
//			value=new Student(Integer.toString(i),i,i);
//			
//			HashMapSt.put(i, value);
//		}
		
//		System.out.println("Hash map: ");
//		
//		for(Student cheie:HashMapSt.values())
//			System.out.println(cheie.toString());
//		
//		for(Integer cheie: HashMapSt.keySet())
//		{
//			System.out.println(cheie);
//		}
		
		
//		/////////////////////////////////////////////////
		
//		Set<Integer> setStud=HashMapSt.keySet();
//		Iterator<Integer> it=setStud.iterator();
//		
//		System.out.println("Set chei map:");
//		for(;it.hasNext();)
//			System.out.println(it.next());
		
		
		/////////////////////////////////////////////////
		
		Set<Student> HashSetSt=new HashSet<>();
		
		Student value;
		
		for(int i=1;i<=5;i++)
		{
			value=new Student(Integer.toString(i),i,i);
			HashSetSt.add(value);
		}
		
		System.out.println("Set: ");
		Iterator<Student> itSet=HashSetSt.iterator();
		for(;itSet.hasNext();)
			System.out.println(itSet.next().toString());

		
	}

}
