package Executor;

import java.io.File;
import java.io.IOException;

/**
 * Created by sjuyal on 29/11/14.
 */
public class TempTest {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    System.setProperty("user.dir", "C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\");
                    //Process p = Runtime.getRuntime().exec("cmd.exe /c start java -cp C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\inertdriver.jar Sample");
                    //Process p = Runtime.getRuntime().exec("cmd.exe /c start C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\batfile.bat");

                    Process p = Runtime.getRuntime().exec("cmd.exe /c start java -cp C:\\\\Users\\\\sjuyal\\\\Desktop\\\\SeleniumProject\\\\dump\\\\inertdriver.jar Sample", null, new File("C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump"));

                    //Process p = Runtime.getRuntime().exec("cmd.exe /c start & start /k \"java -cp C:\\\\Users\\\\sjuyal\\\\Desktop\\\\SeleniumProject\\\\dump\\\\inertdriver.jar Sample\"");
                    p.waitFor();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }.run();
    }
}
