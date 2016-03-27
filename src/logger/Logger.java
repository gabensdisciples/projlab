package logger;

/**
 * Logger util class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Logger {
  private static int tabCount = -1;
  
  /**
   * Creates indentation while logging the call chain.
   * @param string - string to log
   */
  public static void log(String string) {
    tabCount++;
    for (int i = 0; i < tabCount; i++) {
      System.out.print("\t");
    }
    System.out.println(string);
  }

  public static void logout() {
    tabCount--;
  }
}
