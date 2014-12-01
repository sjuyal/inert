package data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arpit Bhayani on 11/2/14.
 */
public class FileItemMultipart {

    private String fileName = null;
    private Map<String,String> info = null;
    private StringBuilder fileContent = null;

    private String id = null;

    public FileItemMultipart() {
        info = new HashMap<String, String>();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public StringBuilder getFileContent() {
        return fileContent;
    }

    public void setFileContent(StringBuilder fileContent) {
        this.fileContent = fileContent;
    }

    public void addToMap(String key, String value) {
        info.put(key,value);
    }

    public String getValue(String key) {
        return info.get(key);
    }

    public boolean hasKey(String key ) {
        return info.containsKey(key);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FileItemMultipart{" +
                "fileName='" + fileName + '\'' +
                ", info=" + info +
                ", fileContent=" + fileContent +
                '}';
    }
}
