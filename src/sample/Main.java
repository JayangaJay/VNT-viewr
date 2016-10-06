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
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Jayanga Jayathilake 2016.08.20 0200H
 */
public class Main extends Application {

    /**
     * Root Window Stage to be displayed
     */
    public static Stage primaryStage;
    public static File SelectedFile;
    protected static Main main;
    static StringBuilder sb = new StringBuilder();
    private static Controller controller;
    public List<File> files;

    /**
     * Command Line Arguments
     *
     * @param args
     */
    public static void main(String[] args) {
        for (String argument : args) {
            System.out.println(argument);
        }
            System.out.println("Sys Env`s");
        for(Map.Entry<String, String> paths : System.getenv().entrySet()){
            System.out.println(paths.getKey()+ " : " + paths.getValue());
        }
            System.out.println("Sys Props");
        for(Map.Entry<Object, Object> paths : System.getProperties().entrySet()){
            System.out.println(paths.getKey()+ " : " + paths.getValue());
        }
//        System.out.println(System.getProperty("user.home") + File.separatorChar + "My Documents");


        main = new Main();
        launch(args);
    }

    public void LoadFile(File selectedFile) {
        SelectedFile = selectedFile;
        if (SelectedFile != null || SelectedFile.isFile()) {
            try {
                System.out.println("-------------  form note Object ***********   ----------");
                NoteClass noteClass = new NoteClass(selectedFile);
                System.out.println(noteClass.getFormattedBodyText());
                System.out.println("-------------  end Note Obj***********   ----------");
                loadedController loadedContoller = (loadedController) loadNextScene(primaryStage, "loaded.fxml");
                loadedContoller.setNoteOntoScene(new NoteClass(selectedFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
//                        setSelectedFile(file.getAbsoluteFile());
                        LoadFile(file.getAbsoluteFile());
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
     * Load next Scene on given Stage
     * @param primaryStage Stage going to show Next Scene
     * @param fxmlLocation FXML file containing next designed scene
     * @return Controller of FXML file
     * @throws IOException if there is no such File
     */
    Object loadNextScene(Stage primaryStage, String fxmlLocation) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("loaded.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
        Scene nxtScn = new Scene(loader.load());
        primaryStage.setScene(nxtScn);
        primaryStage.show();
        return loader.getController();
    }



    /**
     * Loading a new Stage on a given window
     */
    void showNewStageWithOwner(Stage owner,String fxmlLocaiton) throws IOException {
        Stage newStage = new Stage();
        newStage.setTitle("About VNT Viewer");
        newStage.setFullScreen(false);
        newStage.initOwner(owner.getScene().getWindow());
        loadNextScene(newStage,fxmlLocaiton);
    }
}
