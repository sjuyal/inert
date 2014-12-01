package parser;

import java.util.ArrayList;

/**
 * Created by Arpit Bhayani on 9/4/14.
 */
public class BallInfo {

    String text = null;

    @Override
    public String toString() {
        return "BallInfo{" +
                "text='" + text + '\'' +
                ", ballNumberString='" + ballNumberString + '\'' +
                ", bowlerName='" + bowlerName + '\'' +
                ", batsmanName='" + batsmanName + '\'' +
                ", specialInstance='" + specialInstance + '\'' +
                ", runsScored=" + runsScored +
                ", overNumber=" + overNumber +
                ", ballNumber=" + ballNumber +
                ", tags=" + tags +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String ballNumberString = null;

    String bowlerName = null;
    String batsmanName = null;
    String specialInstance = null;

    int runsScored = 0;
    int overNumber = 0;
    int ballNumber = 0;

    ArrayList<String> tags = null;

    public String getBallNumberString() {
        return ballNumberString;
    }

    public void setBallNumberString(String ballNumberString) {
        this.ballNumberString = ballNumberString;
    }

    public String getBowlerName() {
        return bowlerName;
    }

    public void setBowlerName(String bowlerName) {
        this.bowlerName = bowlerName;
    }

    public String getBatsmanName() {
        return batsmanName;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public String getSpecialInstance() {
        return specialInstance;
    }

    public void setSpecialInstance(String specialInstance) {
        this.specialInstance = specialInstance;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getOverNumber() {
        return overNumber;
    }

    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
    }

    public int getBallNumber() {
        return ballNumber;
    }

    public void setBallNumber(int ballNumber) {
        this.ballNumber = ballNumber;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

}
