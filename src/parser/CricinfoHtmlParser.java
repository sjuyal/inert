package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by Arpit Bhayani on 8/31/14.
 */
public class CricinfoHtmlParser {

    /**
     * This method parses the match report and returns a list of String
     * that contains each line of the report as one element in the
     * list.
     *
     * @param reportFile
     */

    public static ArrayList<String> parseMatchReport(String reportFile) {

        ArrayList<String> l = new ArrayList<String>();
        File matchReportFile = new File(reportFile);
        //File parsedMatchReportFile = new File(parsedFile);

        Document document = null;

        try {
            document = Jsoup.parse(matchReportFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document == null) {
            System.err.println("[DEVILO] Error in the JSOUP Parser.");
            return new ArrayList<String>();
        }

        assert document != null;

        Elements elements = document.select("p[class=news-body]");

        boolean skip = true;

        for (Element element : elements) {
            String text = element.text().trim();
            if (text.length() > 0) {
                if (skip == true) {
                    skip = false;
                    continue;
                }
                l.add(text);
            }
        }

        return l;

        /*BufferedWriter fileWriter = null;

        try {

            boolean skip = true;
            fileWriter = new BufferedWriter(new FileWriter(parsedMatchReportFile));

            assert fileWriter != null;

            for (Element element : elements) {
                String text = element.text().trim();

                if (text.length() > 0) {
                    if (skip == true) {
                        skip = false;
                        continue;
                    }
                    fileWriter.write(text);
                    fileWriter.write('\n');
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    public static ArrayList<BallInfo> parseCommentary(String commentaryPath) {

        ArrayList<BallInfo> l = new ArrayList<BallInfo>();
        String decimalPattern = "([0-9]*)\\.([0-9]*)";

        File commentaryFile = new File(commentaryPath);
        //File parsedMatchReportFile = new File(parsedCommentaryFile);

        Document document = null;

        try {
            document = Jsoup.parse(commentaryFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (document == null) {
            System.err.println("[DEVILO] Error in the JSOUP Parser.");
            return new ArrayList<BallInfo>();
        }

        assert document != null;

        Elements elements = document.select("table[class=commsTable] tr");

        for (Element eachRow : elements) {
            Elements data = eachRow.select("p[class=commsText]");
            if (data.size() > 1) {

                boolean match = false;
                BallInfo b = new BallInfo();

                String text = data.get(0).text().trim();
                if (text.length() > 0) {

                    match = Pattern.matches(decimalPattern, text);
                    if (match == true) {

                        String[] overAndBall = text.split("[.]");
                        b.setBallNumberString(text);
                        b.setOverNumber(Integer.parseInt(overAndBall[0]));
                        b.setBallNumber(Integer.parseInt(overAndBall[1]));

                        //fileWriter.write(text);
                        //fileWriter.write('\n');
                    }
                }
                if (match == true) {
                    text = data.get(1).text().trim();
                    populateBallInfo(text, b);
                    l.add(b);
                    //fileWriter.write(text);
                    //fileWriter.write('\n');
                }
            }
        }

        return l;

        /*try {
            fileWriter = new BufferedWriter(new FileWriter(parsedMatchReportFile));

            assert fileWriter != null;

            for (Element eachRow : elements) {
                //System.out.println("ELEMENT");
                Elements data = eachRow.select("p[class=commsText]");


                if (data.size() > 1) {
                    boolean match = false;
                    String text = data.get(0).text().trim();
                    if (text.length() > 0) {
                        match = Pattern.matches(decimalPattern, text);
                        if (match == true) {
                            fileWriter.write(text);
                            fileWriter.write('\n');
                        }
                    }

                    if (match == true) {
                        text = data.get(1).text().trim();
                        fileWriter.write(text);
                        fileWriter.write('\n');
                    }

                }

                /*for (int i = 0 ; i < data.size() ; i++ ) {
                    Element eachData = data.get(i);
                    String text = eachData.text().trim();
                    if (text.length() > 0) {
                        fileWriter.write("AAAAA : ");
                        fileWriter.write(text);
                        fileWriter.write('\n');
                    }
                }


            }

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    private static void populateBallInfo(String text, BallInfo b) {

        b.text = text;
        StringBuilder tempText = new StringBuilder();

        int i = 0;
        for (; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch == ',') {
                break;
            }

            tempText.append(ch);
        }

        b.setBowlerName(tempText.toString().split(" to ")[0]);
        b.setBatsmanName(tempText.toString().split(" to ")[1]);

        tempText.setLength(0);

        for (i++; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ',') {
                break;
            }
            tempText.append(ch);
        }

        String ballEvent = tempText.toString().trim();


        String[] array = ballEvent.toString().split("[ ]");

        String t0 = null, t1 = null;

        if (array.length >= 1)
            t0 = array[0].toLowerCase();

        if (array.length >= 2)
            t1 = array[1].toLowerCase();

        if (t0.equalsIgnoreCase("no") && t1.equalsIgnoreCase("run")) {
            b.setRunsScored(0);
            b.setSpecialInstance("RUN");
        } else if (t0.equalsIgnoreCase("OUT")) {
            b.setRunsScored(0);
            b.setSpecialInstance("OUT");
        } else if (t0.equalsIgnoreCase("FOUR")) {
            b.setRunsScored(4);
            b.setSpecialInstance("FOUR");
        } else if (t0.equalsIgnoreCase("SIX")) {
            b.setRunsScored(6);
            b.setSpecialInstance("SIX");
        } else {
            try {
                Integer numberOfRuns = Integer.parseInt(t0);
                b.setRunsScored(numberOfRuns);
                b.setSpecialInstance("RUN");
            } catch (Exception e) {
                System.err.println("Other option : " + t0 + " for ball " + b.ballNumberString);
                b.setSpecialInstance(t0);
            }
        }

        tempText.setLength(0);

        for (i++; i < text.length(); i++) {
            char ch = text.charAt(i);
            tempText.append(ch);
        }

        ArrayList<String> tags = new ArrayList<String>();
        String ballDetailText = tempText.toString().toLowerCase();

        b.setTags(tags);

    }

}