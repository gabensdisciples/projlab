package logger;

public class Logger {
	private static int tabcount;

	public logger() {
		tabcount = 0;
	}

	public static void logTab() {
		for (int i = 0; i < tabcount; i++)
			System.out.print("\t");
	}

	public static void login() {
		tabcount++;
	}

	public static void logout() {
		tabcount--;
	}
}
