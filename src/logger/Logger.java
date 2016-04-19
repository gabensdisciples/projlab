package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Logger util class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Logger {
  private static int tabCount = -1;
  private static String current = "";

  /**
   * Creates indentation while logging the call chain. Logs to stdout and to a
   * file.
   * 
   * @param string
   *          - string to log
   */
  public static void log(String string) {
    File logFile = new File("skeleton.log");
    if (!logFile.exists()) {
      try {
        logFile.createNewFile();
      } catch (IOException exception) {
        exception.printStackTrace();
      }
    }
    FileOutputStream logFileStream;

    tabCount++;
    for (int i = 0; i < tabCount; i++) {
      string = "\t" + string;
    }
    current += string + "\n";
    System.out.println(string);
    try {
      logFileStream = new FileOutputStream(logFile, true);
      BufferedWriter logBufferedWriter = new BufferedWriter(new OutputStreamWriter(logFileStream));
      logBufferedWriter.write(string);
      logBufferedWriter.newLine();
      logBufferedWriter.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }

  }

  public static String currentOut(){
    String tmp = current;
    current = "";
    return tmp;
  }
  
  public static void logout() {
    tabCount--;
  }
}
