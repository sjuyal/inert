package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CricsterStruct
 * Created by Arpit Bhayani on 9/1/14.
 */
public class CricsterStruct {

    private static ArrayList<String> cricketGlossary = null;
    private static CricsterStruct instance = null;

    protected CricsterStruct() {
        // Exists only to defeat instantiation.
        cricketGlossary = new ArrayList<String>();
    }

    public static CricsterStruct getInstance() {
        if(instance == null) {
            instance = new CricsterStruct();
            initialize();
        }
        return instance;
    }

    private static void initialize() {

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("G:\\WebMining\\Monsoon2014\\Assignments\\Assignment2\\src\\Cricster\\src\\com\\dataclox\\cricster\\cricglossary.txt"));
            String cricketTerm = null;

            while( (cricketTerm = bufferedReader.readLine()) != null ) {
                cricketGlossary.add(cricketTerm);
            }
            bufferedReader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    ArrayList<BallInfo> commentaryFirstInnings = new ArrayList<BallInfo>();
    ArrayList<BallInfo> commentarySecondInnings = null;

    private boolean is_a_ball( String ball ) {

        try {
            Float.parseFloat(ball);
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public void populateFirstInnCommentary(String parsedCommentaryFile) throws IOException {

        BufferedReader fileReader = new BufferedReader(new FileReader(parsedCommentaryFile));

        String line = null;

        while( (line = fileReader.readLine()) != null ) {
            if( is_a_ball(line) ) {
                break;
            }
        }

        /**
         * assert that the innings started :) and the first ball is bowled.
         */

        boolean commentaryEnded = false;
        while (commentaryEnded == false) {

            String[] overAndBall = line.split("[.]");

            BallInfo b = new BallInfo();
            b.setBallNumberString(line);
            b.setOverNumber(Integer.parseInt(overAndBall[0]));
            b.setBallNumber(Integer.parseInt(overAndBall[1]));

            StringBuilder text = new StringBuilder();

            while( (line = fileReader.readLine()) != null ) {
                if(is_a_ball(line))
                    break;

                if(is_a_ball(line.substring(0,line.indexOf(' '))) && line.substring(line.indexOf(' ') + 1).indexOf(' ') != -1 ) {
                    commentaryEnded = true;
                    break;
                }


                if( line.indexOf('\"') == -1 ) {
                    text.append(line);
                    text.append(' ');
                }
            }

            //System.out.println("TEXT : " + text);
            /*StringBuilder tempText = new StringBuilder();

            int i = 0;
            for( ; i < text.length() ; i++ ) {

                char ch = text.charAt(i);

                if( ch == ',' ) {
                    break;
                }

                tempText.append(ch);
            }

            b.setBowlerName(tempText.toString().split(" to ")[0]);
            b.setBatsmanName(tempText.toString().split(" to ")[1]);

            tempText.setLength(0);

            for( i++ ; i < text.length() ; i++ ) {
                char ch = text.charAt(i);
                if( ch == ',' ) {
                    break;
                }
                tempText.append(ch);
            }

            String ballEvent = tempText.toString().trim();


            String[] array = ballEvent.toString().split("[ ]");

            String t0 = null , t1 = null;

            if(array.length >= 1)
                t0 = array[0].toLowerCase();

            if(array.length >= 2)
                t1 = array[1].toLowerCase();

            if( t0.equalsIgnoreCase("no") && t1.equalsIgnoreCase("run")) {
                b.setRunsScored(0);
                b.setSpecialInstance("RUN");
            }
            else if( t0.equalsIgnoreCase("OUT") ) {
                b.setRunsScored(0);
                b.setSpecialInstance("OUT");
            }
            else if(t0.equalsIgnoreCase("FOUR")) {
                b.setRunsScored(4);
                b.setSpecialInstance("FOUR");
            }
            else if(t0.equalsIgnoreCase("SIX")) {
                b.setRunsScored(6);
                b.setSpecialInstance("SIX");
            }
            else {
                try {
                    Integer numberOfRuns = Integer.parseInt(t0);
                    b.setRunsScored(numberOfRuns);
                    b.setSpecialInstance("RUN");
                }
                catch (Exception e) {
                    System.err.println("Other option : " + t0);
                    b.setSpecialInstance(t0);
                }
            }

            tempText.setLength(0);

            for( i++ ; i < text.length() ; i++ ) {
                char ch = text.charAt(i);
                tempText.append(ch);
            }

            ArrayList<String> tags = new ArrayList<String>();
            String ballDetailText = tempText.toString().toLowerCase();

            for( int j = 0 ; j < cricketGlossary.size() ; j++ ) {
                if( ballDetailText.contains(cricketGlossary.get(j)) ) {
                    tags.add(cricketGlossary.get(j));
                }
            }

            b.setTags(tags);

            commentaryFirstInnings.add(b);*/

            System.out.println(b);

            if( line == null )
                break;

        }

        fileReader.close();

    }

}
