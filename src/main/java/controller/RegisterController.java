package controller;

import driver.MySQLDrive;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import stage.TestStage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static controller.TestController.ADMINPASSWORD;

public class RegisterController{
    @FXML
    public TextField r_name;
    @FXML
    public PasswordField r_password;
    @FXML
    public PasswordField r_surepassword;
    @FXML
    public PasswordField admin_password;
    @FXML
    public Button r_register;
    @FXML
    public Button r_back;
    @FXML
    public Label r_error;
    @FXML
    public TextArea r_success;
    @FXML
    private RadioButton selectCustom;
    @FXML
    private RadioButton selectAdministrator;

    /**
     * 点击注册按钮，得到注册信息
     * @throws SQLException
     */
    public void actionRegister()throws SQLException{
        if(r_name.getText().equals("")||r_password.getText().equals("")||r_surepassword.getText().equals("")){

        }else if(admin_password.getText().equals(ADMINPASSWORD)){
            Register1(r_name.getText(),r_password.getText());
            r_error.setVisible(false);
            r_success.setVisible(true);
        }
        else if(Register2(r_name.getText(),r_password.getText())){
            r_error.setVisible(false);
            r_success.setVisible(true);
        }
        else {
            r_error.setVisible(true);
        }


    }

    /**
     * 点击返回按钮，退出注册界面
     * @throws Exception
     */
    public void registerBack() throws Exception {
        TestStage testStage =new TestStage();
        testStage.start(new Stage());
        Stage stage  = (Stage)r_back.getScene().getWindow();
        stage.close();
    }

    /**
     * 注册管理员账号，保存入数据库
     * @param name 管理员账号
     * @param password 管理员密码
     * @throws SQLException
     */
    public static void Register1(String name, String password) throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("insert into administrator values(?,?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        preparedStatement.execute();
    }

    /**
     * 注册顾客账号，保存入数据库
     * @param name 顾客账号
     * @param password 顾客密码
     * @return 保存数据库成功，返回true
     * @throws SQLException
     */
    public static Boolean Register2(String name, String password) throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("insert into customer values(?,?)");
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,password);
        preparedStatement.execute();
        return true;
    }

    /**
     * 点击选择顾客身份
     */
    public void customer(){
        if(selectAdministrator.isSelected())
        {
            selectAdministrator.setSelected(false);
        }
        selectCustom.setSelected(true);
    }

    /**
     * 点击选择管理员身份，和顾客二选一
     */
    public void administrator(){
        if(selectCustom.isSelected())
        {
            selectCustom.setSelected(false);
        }
        selectAdministrator.setSelected(true);
    }

}
