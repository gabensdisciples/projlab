package logger;

public class Logger {
	private static int Tabcount = -1;

	public static void Log(String s) {
		Tabcount++;
		for (int i = 0; i < Tabcount; i++)
			System.out.print("\t");
		System.out.println(s);
	}

	public static void Logout() {
		Tabcount--;
	}
}
