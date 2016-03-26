package logger;

public class Logger {
	private static int tabcount = -1;

	public static void Log(String s) {
		tabcount++;
		for (int i = 0; i < tabcount; i++)
			System.out.print("\t");
		System.out.println(s);
	}

	public static void Logout() {
		tabcount--;
	}
}
