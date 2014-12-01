package Executor;

import java.util.HashMap;

/**
 * Created by Arpit Bhayani on 11/11/14.
 */
public class DataStore {

    private static DataStore instance = null;

    private int id = 0;
    private static HashMap<String,Command> map = null;

    private DataStore() {
        id = 0;
        map = new HashMap<String, Command>();
    }

    public static DataStore getInstance() {
        if(instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public int getNewIdAndCreateCommand() {
        map.put(++id + "", new Command());
        return id;
    }

    public static void printCommand(String id) {
        System.out.println(map.get(id));
    }

    public static Command getCommand(String id) {
        return map.get(id);
    }

    public static boolean setBrowserForId(String id, boolean val, String bid) {
        Command c = map.get(id);
        if( c == null )
            return false;

        if( bid.equals("gc") ) {
            c.setGC(val);
        }
        else if( bid.equals("ff") ) {
            c.setFF(val);
        }
        else {
            c.setIE(val);
        }

        return true;
    }

    public static boolean setFileNameForId(String id, String fileName) {
        Command c = map.get(id);
        if( c == null )
            return false;
        c.setFileName(fileName);
        return true;
    }

}
