package Executor;

/**
 * Created by Arpit Bhayani on 11/11/14.
 */
public class Command {

    private boolean isGC = false;
    private boolean isFF = false;
    private boolean isIE = false;

    private String fileName = null;

    public boolean isGC() {
        return isGC;
    }

    public void setGC(boolean isGC) {
        this.isGC = isGC;
    }

    public boolean isFF() {
        return isFF;
    }

    public void setFF(boolean isFF) {
        this.isFF = isFF;
    }

    public boolean isIE() {
        return isIE;
    }

    public void setIE(boolean isIE) {
        this.isIE = isIE;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        //        "        <filename>");
        //sb.append(this.fileName);
        //sb.append("</filename>\n" +
        "        <browser>\n" +
                "        <browserName name= \"chrome\">");
        sb.append(isGC);
        sb.append("</browserName>\n" +
                "        <browserName name= \"firefox\">");
        sb.append(isFF);
        sb.append("</browserName>\n" +
                "        <browserName name= \"ie\">");
        sb.append(isIE);
        sb.append("</browserName>\n" +
                "        </browser>");

        /*return "Command{" +
                "isGC=" + isGC +
                ", isFF=" + isFF +
                ", isIE=" + isIE +
                ", fileName='" + fileName + '\'' +
                '}';*/
        return sb.toString();
    }
}
