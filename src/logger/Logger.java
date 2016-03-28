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

  /**
   * Creates indentation while logging the call chain.
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
        // TODO Auto-generated catch block
        exception.printStackTrace();
      }
    }
    FileOutputStream logFileStream;

    tabCount++;
    for (int i = 0; i < tabCount; i++) {
      string = "\t" + string;
      // System.out.print("\t");
    }
    System.out.println(string);
    try {
      logFileStream = new FileOutputStream(logFile, true);
      BufferedWriter logBufferedWriter = new BufferedWriter(new OutputStreamWriter(logFileStream));
      logBufferedWriter.write(string);
      logBufferedWriter.newLine();
      logBufferedWriter.close();
    } catch (IOException exception) {
      // TODO Auto-generated catch block
      exception.printStackTrace();
    }

  }

  public static void logout() {
    tabCount--;
  }
}
