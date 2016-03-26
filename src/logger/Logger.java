package logger;

public class Logger {
	private static int tabcount;
	
	public Logger(){
		tabcount = 0;
	}
	
	public static void LogTab(){
		for(int i = 0; i< tabcount;i++)
			System.out.print("\t");
	}
		
	public static void Login(){
		tabcount++;
	}
	
	public static void Logout(){
		tabcount--;
	}
}
