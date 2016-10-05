package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * @author Jayanga Jayathilake 2016.08.20 0200H
 */
public class Main extends Application {

    /**
     * Root Window Stage to be displayed
     */
    public static Stage primaryStage;
    public static File SelectedFile;
    public List<File> files;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
        controller = loader.getController();
        AnchorPane rootPane = loader.load();
        primaryStage.setTitle("VNT Viewer");
        Scene scene = new Scene(rootPane);

        scene.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            if (dragboard.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });

        scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                for (File file : db.getFiles()) {
                    if (file.getName().contains(".vnt")) {
                        files = db.getFiles();
//                        controller.hideDragLabel();
                        System.out.println(file.getAbsoluteFile());

                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong File. Load VNT SelectedFile to view", "Unreadable File", JOptionPane.ERROR_MESSAGE);
                    }
                }
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;
    }

    /**
     * Command Line Arguments
     *
     * @param args
     */
    public static void main(String[] args) {
        for (String argument : args) {
            System.out.println(argument);
        }
        /*
        for(Map.Entry<String, String> paths : System.getenv().entrySet()){
            System.out.println(paths.getKey()+ " " + paths.getValue());
        }
        System.out.println(System.getProperty("user.home") + File.separatorChar + "My Documents");
        */
        launch(args);
    }

    public static void LoadFile(File selectedFile) {
        SelectedFile = selectedFile;
        if(SelectedFile != null){

        }
    }
}
