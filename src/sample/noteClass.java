package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

/**
 * Created by Jayanga on 10/5/2016.
 */
public class noteClass {
    String version;
    String charset;
    String bodyText;
    Date createdDate;
    Date lastModifiedDate;
    File noteFileLocation;

    void readNote() {
        try (BufferedReader bufRead = new BufferedReader(new FileReader(noteFileLocation))) {
            StringBuilder sb = new StringBuilder();
            String s = bufRead.readLine();
            while (s != null) {
                sb.append(s);
                sb.append(System.lineSeparator());
                System.out.println(sb);
                s = bufRead.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
