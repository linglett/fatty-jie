package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorStage extends Application {
    public static Stage LOGIN=null;
    @Override
    public void start(Stage primaryStage) throws IOException {
        LOGIN=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/Administractor.fxml"));
        primaryStage.setTitle("超市管理系统");
        primaryStage.setScene(new Scene(root, 762, 573));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String... args) {
        launch(args);
    }
}
