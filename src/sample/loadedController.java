package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Jayanga on 10/5/2016.
 */
public class loadedController implements Initializable {

    @FXML
    private Label loadedFileLocation;


    @FXML
    private TextArea textOnFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setTextOnFile(String txt) {
        textOnFile.setText(txt);
    }

    public TextArea getTextOnFile() {
        return textOnFile;
    }

    public void setLoadedFileLocation(String filePath) {
        loadedFileLocation.setText(filePath);
    }

}