package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
            setCenterBorderPane();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
/*
        Main.primaryStage.getScene().onDragDoneProperty().addListener(new ChangeListener<EventHandler<? super DragEvent>>() {
            @Override
            public void changed(ObservableValue<? extends EventHandler<? super DragEvent>> observable, EventHandler<? super DragEvent> oldValue, EventHandler<? super DragEvent> newValue) {
                System.out.println("got Changed @IndexController" + oldValue + " -> " + newValue);
            }
        });

*/
//        resources.keySet().forEach(System.out::println);
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
            final loadedController loadedController = loadedPane.getController();
            centerBorderPane = new BorderPane(loadedPane.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
