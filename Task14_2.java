package Project2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Task14_2 extends Application {
    public TextArea textarea = new TextArea();
    public int threadNo = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
       // TextArea textarea = new TextArea();

        Scene scene = new Scene(new ScrollPane(textarea), 500, 150);
        primaryStage.setTitle("Exercise31_04Sever");
        primaryStage.setScene(scene);
        primaryStage.show();
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                textarea.appendText("Exercise31_04Sever started at " + new Date() + '\n');

                do {
                    Socket socket = serverSocket.accept();

                    Platform.runLater(() -> {
                        textarea.appendText("Starting thread " + threadNo++ + '\n');

                        InetAddress inetAddress = socket.getInetAddress();
                        textarea.appendText("Client IP /" +
                                inetAddress.getHostAddress() + '\n');

                    });
                    new Thread(new HandleAClient(socket)).start();
                } while (true);
            }
            catch(IOException ex) {
                System.err.println(ex);
            }
        }).start();
    }

    class HandleAClient implements Runnable {
        private Socket socket;


        public HandleAClient(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {

                DataOutputStream outputToClient = new DataOutputStream(
                        socket.getOutputStream());

                outputToClient.writeUTF("You are visitor " + threadNo);
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}
