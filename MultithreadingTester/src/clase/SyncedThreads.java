package clase;

public class SyncedThreads implements Runnable
{
	private static int ct1=0;
	private static int ct2=0;
	private String nume;
	private static Object mutex=new Object();
	
	public SyncedThreads(String nume) 
	{
		this.nume=nume;
	}
	
	public void incrementare()
	{
		synchronized (mutex) 
		{
			System.out.println(String.format("Firul %s: ct1=%d, ct2=%d", nume,ct1,ct2));
			ct1++;
			
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ct2++;
		}
	}
	
	
	
	@Override
	public void run() 
	{
		for(int i=0;i<5;i++)
			incrementare();
		
	}
	
}
