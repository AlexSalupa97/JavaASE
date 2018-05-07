package main;

import clase.Audio;
import clase.Media;
import clase.StreamAudio;

public class Main {

	public static void main(String[] args) 
	{
		
		Audio a=null;
		try {
			a=new Audio("audio", 100, 40);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StreamAudio sa=null;
		
		try {
			 sa=new StreamAudio("audio", 50, 40);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sa.citesteStreamFisier("fisier.txt");
		
		sa.returneazaPlayer();
		

	}

}
