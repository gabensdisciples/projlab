package logger;

public class Logger {
	private static int tabCount = -1;

	public static void Log(String s) {
		tabCount++;
		for (int i = 0; i < tabCount; i++)
			System.out.print("\t");
		System.out.println(s);
	}

	public static void Logout() {
		tabCount--;
	}
}
