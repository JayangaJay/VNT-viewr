package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private static Controller controller;

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
     * Command Line Arguments
     *
     * @param args
     */
    public static void main(String[] args) {
        for (String argument : args) {
            System.out.println(argument);
        }
        launch(args);
    }

    public static void LoadFile(File selectedFile) {
        SelectedFile = selectedFile;
//        System.out.println(SelectedFile);
        if (SelectedFile != null || SelectedFile.isFile()) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
            try (BufferedReader bufRead = new BufferedReader(new FileReader(SelectedFile))) {
//                System.out.println();
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
//            System.out.println("here ok");
//            controller.setCenterBorderPane();
        }
    }
}
