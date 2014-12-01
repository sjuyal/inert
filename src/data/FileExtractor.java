package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 * Created by Arpit Bhayani on 11/2/14.
 */
public class FileExtractor {

    /**
     * The function extracts the file passed using file upload from HTML
     * and stores it in the server data path.
     * For Chrome : ------WebKitFormBoundary
     */
    public static HashMap<String,FileItemMultipart> extractFiles(String body) throws IOException {

        BufferedReader reader = new BufferedReader(new StringReader(body));

        HashMap<String,FileItemMultipart> l = new HashMap<String,FileItemMultipart>();

        String line = reader.readLine();

        while (true) {

            if (line == null)
                break;

            if (line.startsWith("------WebKitFormBoundary")) {

                FileItemMultipart f = new FileItemMultipart();

                while ((line = reader.readLine()) != null && !line.equals("")) {
                    // save in the map
                    int index = line.indexOf(':');
                    if (index != -1) {
                        f.addToMap(line.substring(0, index), line.substring(index));
                    }
                }

                //file content
                StringBuilder fileContent = new StringBuilder();
                while ((line = reader.readLine()) != null && !line.startsWith("------WebKitFormBoundary")) {
                    fileContent.append(line);
                    fileContent.append('\n');
                }
                f.setFileContent(fileContent);

                //set file name
                if (f.hasKey("Content-Disposition")) {
                    String v = f.getValue("Content-Disposition");

                    int index = 0;

                    index= v.indexOf("name");
                    index += 5;
                    String name = v.substring(index+1, v.indexOf('"', index+1));
                    if( name.length() > 0 ) {
                        f.setId(name);
                    }

                    index= v.indexOf("filename");
                    index += 9;
                    String fName = v.substring(index+1, v.indexOf('"', index+1));
                    if( fName.length() > 0 ) {
                        f.setFileName(fName);
                        l.put(name,f);
                    }
                }
            }

        }

        return l;

    }

}
