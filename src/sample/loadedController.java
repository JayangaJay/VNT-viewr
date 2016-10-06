package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Created by Jayanga on 10/5/2016.
 */
public class loadedController extends ControllerClass {

    @FXML
    private Label loadedFileLocation;


    @FXML
    private TextArea textOnFile;

    public TextArea getTextOnFile() {
        return textOnFile;
    }

    public void setTextOnFile(String txt) {
        textOnFile.setText(txt);
    }

    public void setLoadedFileLocation(String filePath) {
        loadedFileLocation.setText(filePath);
    }

}