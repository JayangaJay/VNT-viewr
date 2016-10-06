package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

/**
 * Created by Jayanga on 10/5/2016.
 */
public class loadedController extends ControllerClass {


    @FXML
    private Text createdDate;

    @FXML
    private Text modifiedDate;

    @FXML
    private Label loadedFileLocation;

    @FXML
    private ToggleButton editability;

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

    void setNoteOntoScene(NoteClass note){
//        note.getCreatedDate();
        createdDate.setText(note.getCreatedDate().toString());
        modifiedDate.setText(note.getLastModifiedDate().toString());
//        textOnFile.setText(note.getFormattedBodyText().toString());
        textOnFile.setText(note.toString());
        loadedFileLocation.setText(note.getNoteFileLocation().toString());
    }

    @FXML
    void SetEditorEditable(ActionEvent evt){
        textOnFile.setEditable(true);
    }

}