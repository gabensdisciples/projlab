package logger;

public class Logger {
	private static int tabcount;
	
	public Logger(){
		tabcount = 0;
	}
	
	public void LogTab(){
		for(int i = 0; i< tabcount;i++)
			System.out.print("\t");
	}
		
	public void Login(){
		tabcount++;
	}
	
	public void Logout(){
		tabcount--;
	}
}
