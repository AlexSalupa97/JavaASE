package main;

import clase.SyncedThreads;

public class Main {

	public static void main(String[] args) 
	{
		SyncedThreads st1=new SyncedThreads("thread1");
		SyncedThreads st2=new SyncedThreads("thread2");

		Thread t1=new Thread(st1);
		Thread t2=new Thread(st2);
		
		t1.start();
		t2.start();
	}

}
