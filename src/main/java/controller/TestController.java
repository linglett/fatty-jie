package controller;


import driver.MySQLDrive;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import stage.RegisterStage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TestController {

    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Button sure;
    @FXML
    private Button register;
    @FXML
    private Label error;

    public static String ADMINPASSWORD="343590";

    public void action() throws SQLException {
        if (name.getText().equals("")||password.getText().equals("")) {
            error.setVisible(true);
        }else {
            PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("select * from administrator where name=?and password=?");
            preparedStatement.setString(1,name.getText());
            preparedStatement.setString(2,password.getText());
            ResultSet resultSet =preparedStatement.executeQuery();//Java类集合框架
            if(resultSet.next()){
                error.setText("登陆成功");
                error.setVisible(true);
            }else {
                error.setVisible(true);
            }
        }

    }
    public void registerButtonAction() throws Exception {
        RegisterStage registerStage =new RegisterStage();
        registerStage.start(new Stage());
        Stage stage  = (Stage)register.getScene().getWindow();
        stage.close();
    }

}
