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

/**
 * @author Jayanga Jayathilake 2016.08.20 0200H
 */
public class Main extends Application {

    /**
     * Root Window Stage to be displayed
     */
    public static Stage primaryStage;
    public static File SelectedFile;
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
//        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
//        try {
//            String s = "20160623T193540";
//            String[] x = s.split("T");
//            System.out.println(df.parse(x[0]+" "+ x[1]));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        launch(args);
    }

    public static void LoadFile(File selectedFile) {
        SelectedFile = selectedFile;
//        System.out.println(SelectedFile);
        if (SelectedFile != null || SelectedFile.isFile()) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("index.fxml"));
            try {
                System.out.println("-------------  form note Object ***********   ----------");
                NoteClass noteClass = new NoteClass(selectedFile);
                System.out.println(noteClass.getFormattedBodyText());
                System.out.println("-------------  end Note Obj***********   ----------");

            } catch (IOException e) {
                e.printStackTrace();
            }
            /*
            try (BufferedReader bufRead = new BufferedReader(new FileReader(SelectedFile))) {
                String s = bufRead.readLine();
                while (s != null) {
                    if (s.startsWith("DCREATED")) {
                        String date[] = s.split(":")[1].split("T");
                        DateFormat df = new SimpleDateFormat("yyyyMMdd HHmmss");
                        System.out.println(df.parse(date[0] + " " + date[1]));

                    } else if (s.startsWith("BODY")) {
//                        System.out.println(s.substring(13,18));
                        System.out.println(s.substring(45));
                        String bodyText = s.substring(45);
                        String[] split = bodyText.split("=0D=0A");
                        StringBuilder formattedBodyText = new StringBuilder();
                        for (String sentences : split) {
                            System.out.println(sentences);
                            formattedBodyText.append(sentences + "\n");
                        }
                        System.out.println("--------------------");

                        System.out.println(formattedBodyText);
                    }
                    sb.append(s);
                    sb.append(System.lineSeparator());
//                    System.out.println(s);
                    s = bufRead.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            */
//            System.out.println("here ok");
//            controller.setCenterBorderPane();
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
}
