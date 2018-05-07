package main;

import clase.Afisare;
import clase.Calculeaza;
import clase.OperatieMatematica;

@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		OperatieMatematica op=null;
		
		op=(x,y)->x*y;
		System.out.println(op.operatie(4, 5));
		
		System.out.println(Calculeaza.calculeaza(op, 4, 5));
		
		Afisare afisare=(text)->System.out.println(text);
		afisare.afisare("test");
		
	}

}
