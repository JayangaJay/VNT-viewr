package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller extends ControllerClass{

    @FXML
    private Button loadBtn;
    @FXML
    private Text dragLabel;
    @FXML
    private BorderPane centerBorderPane;

    public BorderPane getCenterBorderPane() {
        return centerBorderPane;
    }

    void setCenterBorderPane(AnchorPane anchor) {
        centerBorderPane = new BorderPane(anchor);
    }

    /**
     *
     */
    @FXML
    private void setCenterBorderPane(ActionEvent ev) {
        try {
            FXMLLoader loadedPane = new FXMLLoader(getClass().getResource("loaded.fxml"));
            centerBorderPane = (BorderPane) loadedPane.load();
//            centerBorderPane = new BorderPane(loadedPane.load());

//            final loadedController loadedController = loadedPane.getController();
//            loadedController.setLoadedFileLocation("testeddd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
