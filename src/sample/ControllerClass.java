package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Parent Class of controllers
 * Created by Jaya on 10/6/2016.
 */
public class ControllerClass implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void LoadFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separatorChar + "My Documents"));
        fileChooser.setTitle("Open VNT File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("VNT Files", "*.vnt")
        );
        File selectedFile = fileChooser.showOpenDialog(Main.primaryStage);
        if (selectedFile != null) {
            Main.main.LoadFile(selectedFile);
        }
    }
    @FXML
    void closeWindow(ActionEvent event) {
        System.exit(0);
    }


}
