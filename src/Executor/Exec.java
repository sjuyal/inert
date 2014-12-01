package Executor;

import org.omg.SendingContext.RunTime;

import java.io.*;

/**
 * Created by Arpit Bhayani on 11/11/14.
 */
public class Exec {

    public static String result = "null";
    public static String pre = "null";
    public static Integer count = 0;

    public static void execute(Command cmd) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\TestConfigs\\TestConfig.xml"));
            writer.write(cmd.toString());
            writer.close();

            File f = new File(cmd.getFileName());
            BufferedWriter writerBat = new BufferedWriter(new FileWriter("C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\batFile.bat"));
            writerBat.write("cd C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\");
            writerBat.newLine();
            writerBat.write("javac -cp inertdriver.jar " + f.getName());
            writerBat.newLine();
            writerBat.write("java -cp C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\inertdriver.jar " + f.getName().substring(0, f.getName().length() - 5));
            writerBat.close();
            //System.out.println("javac -cp inertdriver.jar " + f.getName().substring(0, f.getName().length() - 5));
            Process p = Runtime.getRuntime().exec("C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\batFile.bat");

            String line;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            int counter = 0;
            while ((line = in.readLine()) != null) {
                result = line;
            }
            in.close();
            result = "done";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
