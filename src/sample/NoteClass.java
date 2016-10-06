package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Note Object of vnt Viewer
 * Created by Jayanga on 10/5/2016.
 */
public class NoteClass {

    private String softwareVersion;
    private String charset;
    private String rawBodyText;
    private String bodyText;
    private StringBuilder formattedBodyText = new StringBuilder();
    private Date createdDate;
    private Date lastModifiedDate;
    private File noteFileLocation;
    private StringBuilder noteAsText = new StringBuilder();

    public NoteClass(File noteLocation) throws IOException {
        if (noteLocation == null) {
            throw new IOException("File Location is not set", new IOException().getCause());
        } else {
            this.noteFileLocation = noteLocation;
            readRawNote();
        }
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public String getCharset() {
        return charset;
    }

    public StringBuilder getFormattedBodyText() {
        return formattedBodyText;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public File getNoteFileLocation() {
        return noteFileLocation;
    }

    public StringBuilder getNoteAsText() {
        return noteAsText;
    }

    /**
     * Reading Raw Note data
     */
    private void readRawNote() {
        try (BufferedReader bufRead = new BufferedReader(new FileReader(noteFileLocation))) {
            String s = bufRead.readLine();
            while (s != null) {
                if (s.startsWith("VERSION")) {
                    softwareVersion = s.split(":")[1];
                } else if (s.startsWith("BODY")) {
                    rawBodyText = s;
                } else if (s.startsWith("DCREATED")) {
                    createdDate = parseDate(s);
                } else if (s.startsWith("LAST-MODIFIED")) {
                    lastModifiedDate = parseDate(s);
                }
                noteAsText.append(s);
                noteAsText.append(System.lineSeparator());
//                System.out.println(noteAsText);
                s = bufRead.readLine();
            }
            parseRawBodyText(rawBodyText);
        } catch (IOException e) {
            System.out.println("File cannot found on given location : " + noteFileLocation.getAbsolutePath());
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Date cannot be parsed" + e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Parsing Date on vnt file
     *
     * @param dateText
     * @return parsed Date
     */
    private Date parseDate(String dateText) throws ParseException {
        String date[] = dateText.split(":")[1].split("T");
        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
        return df.parse(date[0] + " " + date[1]);
    }

    /**
     * Parsing Raw Body text into complete txt on the true file
     *
     * @param rawtext
     */
    private void parseRawBodyText(String rawtext) {
        rawtext = (rawtext == null) ? rawBodyText : rawtext;
        charset = rawtext.substring(13, 18);
        bodyText = rawtext.substring(45);
        String[] splitSentences = bodyText.split("=0D=0A");
        for (String sentences : splitSentences) {
            formattedBodyText.append(sentences + "\n");
        }
    }

    @Override
    public String toString() {
        return formattedBodyText.toString();
    }
}
