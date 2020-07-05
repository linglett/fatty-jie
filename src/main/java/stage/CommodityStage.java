package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CommodityStage extends Application {
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/commodityScreen.fxml"));
        primaryStage.setTitle("超市管理系统");
        primaryStage.setScene(new Scene(root, 600, 519));
        primaryStage.show();
    }
}
