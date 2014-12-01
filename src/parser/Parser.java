package parser;

import java.util.ArrayList;

/**
 * Created by Arpit Bhayani on 11/5/14.
 */
public class Parser {

    public Parser() {

    }

    public ArrayList<BallInfo> parseCommentary(String in) {
        return CricinfoHtmlParser.parseCommentary(in);
    }

    public ArrayList<String> parseReport(String in) {
        return CricinfoHtmlParser.parseMatchReport(in);
    }
}
