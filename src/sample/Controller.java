package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button loadBtn;
    @FXML
    private Text dragLabel;


    @FXML
    private BorderPane centerBorderPane;

    @FXML
    void closeWindow(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void LoadFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separatorChar + "My Documents"));
        fileChooser.setTitle("Open VNT File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("VNT Files", "*.vnt")
        );
        File selectedFile = fileChooser.showOpenDialog(Main.primaryStage);
        if (selectedFile != null) {
            Main.LoadFile(selectedFile);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public BorderPane getCenterBorderPane() {
        return centerBorderPane;
    }

    /**
     *
     */
    void setCenterBorderPane() {
        try {
            FXMLLoader loadedPane = new FXMLLoader(getClass().getResource("loaded.fxml"));
            centerBorderPane = new BorderPane(loadedPane.load());
            final loadedController loadedController = loadedPane.getController();
            loadedController.setLoadedFileLocation("testeddd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setCenterBorderPane(AnchorPane anchor) {
        centerBorderPane = new BorderPane(anchor);
    }

}
