package Project2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import java.io.*;
import java.net.*;
public class Task14_1 extends Application {
    DataInputStream fromServer = null;
    public Label label  = new Label("");
    @Override
    public void start(Stage primaryStage) {

            StackPane pane = new StackPane();
            pane.getChildren().add(label);
            Scene scene = new Scene(pane, 300, 75);
            primaryStage.setTitle("Exercise31_04Client");
            primaryStage.setScene(scene);
            primaryStage.show();
            try {
                Socket socket = new Socket("localhost", 8000);
                fromServer = new DataInputStream(socket.getInputStream());
                label.setText(fromServer.readUTF());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

    }
}
