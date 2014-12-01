package web.server;


import Executor.Command;
import Executor.DataStore;
import Executor.Exec;
import data.FileExtractor;
import data.FileItemMultipart;
import freemarker.template.Configuration;
import freemarker.template.Template;
import parser.Parser;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import static spark.SparkBase.externalStaticFileLocation;

/**
 * Created by Arpit Bhayani on 10/30/14.
 */

public class Bootstrap {

    String serverDumpFolder = null;

    Configuration freemarkerConfiguration = null;

    public Bootstrap() {
    }

    private void start() {

        externalStaticFileLocation("C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\src\\src\\web\\view");

        load();

        Spark.get("/getOutput", new Route() {


            @Override
            public Object handle(Request request, Response response) {
                if(!Exec.pre.equalsIgnoreCase(Exec.result) && Exec.result.length()>=2 && !Exec.result.substring(0, 2).equalsIgnoreCase("C:")){
                    Exec.pre = Exec.result;
                    String ret = Exec.result;
                    Exec.result = "null";
                    if(ret.equalsIgnoreCase("null") || ret.equalsIgnoreCase("done")) return ret;
                    return ret + "\n";
                }
                return "null";
            }
        });

        Spark.post("/createId", new Route() {

            @Override
            public Object handle(Request request, Response response) {
                int id = DataStore.getInstance().getNewIdAndCreateCommand();
                response.cookie("id", id+"");
                System.out.println("ID: " + id);
                return true;
            }
        });

        Spark.post("/saveBrowsers", new Route() {

            @Override
            public Object handle(Request request, Response response) {
                /*System.out.println(request.queryParams("gc").contains("checked"));
                System.out.println(request.queryParams("ff").contains("checked"));
                System.out.println(request.queryParams("ie").contains("checked"));*/
                /*System.out.println(request.queryParams("gc"));
                System.out.println(request.queryParams("ff"));
                System.out.println(request.queryParams("ie"));*/

                String id = request.cookie("id");
                System.out.println("ID = " + id);
                DataStore d = DataStore.getInstance();
                d.setBrowserForId(id,Boolean.parseBoolean(request.queryParams("gc")),"gc");
                d.setBrowserForId(id,Boolean.parseBoolean(request.queryParams("ff")),"ff");
                d.setBrowserForId(id,Boolean.parseBoolean(request.queryParams("ie")),"ie");

                return true;
            }
        });

        Spark.post("/exec", new Route() {

            @Override
            public Object handle(Request request, Response response) {

                String id = request.cookie("id");
                Command c = DataStore.getInstance().getCommand(id);
                Exec.execute(c);

                return true;
            }
        });

        Spark.post("/input", new Route() {

            @Override
            public Object handle(Request request, Response response) {

                try {
                    String id = request.cookie("id");
                    String f = saveFiles(FileExtractor.extractFiles(request.body()));
                    DataStore d = DataStore.getInstance();
                    d.setFileNameForId(id,f);

                }
                catch (IOException e){
                    e.printStackTrace();
                    return "File Upload Unsuccessful";
                }
                return "File Upload Successful";
            }
        });

        Spark.get("/input", new Route() {

            @Override
            public Object handle(Request request, Response response) {

                try {

                    Template templateInput = freemarkerConfiguration.getTemplate("input.ftl");
                    StringWriter writer = new StringWriter();
                    templateInput.process(new HashMap<String, String>(), writer);
                    return writer;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


        Spark.post("/process", new Route() {

            @Override
            public Object handle(final Request request, final Response response) {
                HashMap<String, FileItemMultipart> listFiles = null;
                try {
                    listFiles = FileExtractor.extractFiles(request.body());
                    saveFiles(listFiles);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parser parser = new Parser();

                //TODO: Given these 3 files process and find mentions and link them to commentary

                response.redirect("/result");
                return null;
            }
        });


        /**
         * Gets the processed data and displays it.
         */
        Spark.get("/result", new Route() {

            @Override
            public Object handle(Request request, Response response) {

                HashMap<String, Object> data = new HashMap<String, Object>();
                Template fruitPickerTemplate = null;
                StringWriter writer = new StringWriter();

                try {
                    fruitPickerTemplate = freemarkerConfiguration.getTemplate("results.ftl");
                    fruitPickerTemplate.process(data, writer);
                    return writer;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        });


    }

    /**
     * Saves the extracted files from HTTP Request payload into server dump folder.
     *
     * @param l FileItemMultipart extracted from the HTTP request payload.
     * @throws java.io.IOException
     */
    private String saveFiles(HashMap<String, FileItemMultipart> l) throws IOException {

        String fName = null;
        for (String s : l.keySet()) {
            FileItemMultipart f = l.get(s);
            BufferedWriter writer = new BufferedWriter(new FileWriter(serverDumpFolder + f.getFileName()));
            writer.write(f.getFileContent().toString());
            writer.close();
            System.out.println("Successfully uploaded : " + serverDumpFolder + f.getFileName());
            fName = serverDumpFolder + f.getFileName();
        }
        return fName;
    }

    /**
     * Loads all the files from the folders "r" and "c" into a String array.
     * Corresponding match report and commentary files are present in sorted order.
     */
    private void load() {
        freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_21);
        freemarkerConfiguration.setClassForTemplateLoading(Bootstrap.class, "../view/");
        serverDumpFolder = "C:\\Users\\sjuyal\\Desktop\\SeleniumProject\\dump\\";
    }

    public static void main(String[] args) {
        Bootstrap boot = new Bootstrap();
        boot.start();
    }
}