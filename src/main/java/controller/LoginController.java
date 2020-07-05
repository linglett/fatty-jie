package controller;

import driver.MySQLDrive;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import stage.AdministratorStage;
import stage.CommodityStage;
import stage.RegisterStage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Button sure;
    @FXML
    private Button register;
    @FXML
    private RadioButton selectCus;
    @FXML
    private RadioButton selectAdm;
    @FXML
    private Label error;
    public static String ADMINPASSWORD="343590";

    /**
     * 登陆界面，选择登录的方式
     * @throws Exception
     */
    public void action() throws Exception {
        //Todo 是否为空
        if (name.getText().equals("")||password.getText().equals("")) {
            error.setVisible(true);
        }else {
            if(selectAdm.isSelected()){
                if(QueryAd()){
                    error.setText("登陆成功");
                    error.setVisible(true);
                    Stage stage  = (Stage)sure.getScene().getWindow();
                    stage.close();
                    // 打开管理员界面
                    AdministratorStage administratorStage = new AdministratorStage();
                    administratorStage.start(new Stage());

                }
            }
            else if(selectCus.isSelected()){
                if(QueryCu()) {
                    error.setText("登陆成功");
                    error.setVisible(true);
                    Stage stage = (Stage) sure.getScene().getWindow();
                    stage.close();

                    //打开顾客购买界面
                    CommodityController.custom = name.getText();
                    CommodityStage commodityStage = new CommodityStage();
                    commodityStage.start(new Stage());
                }
            }
            else{
                error.setVisible(true);
            }
        }

    }

    /**
     * 点击选择顾客身份登录
     */
    public void clickCus(){
        if(selectAdm.isSelected())
        {
            selectAdm.setSelected(false);
        }
        selectCus.setSelected(true);
    }

    /**
     * 点击选择管理员身份登录，二选一
     */
    public void clickAdm(){
        if(selectCus.isSelected())
        {
            selectCus.setSelected(false);
        }
        selectAdm.setSelected(true);
    }

    /**
     * 点击注册按钮，打开注册界面
     * @throws Exception
     */
    public void registerButtonAction() throws Exception {
        RegisterStage registerStage = new RegisterStage();
        registerStage.start(new Stage());
    }

    /**
     * 查询管理员的账号密码是否正确
     * @return 如果账号密码正确则返回true，反之返回false
     * @throws SQLException
     */
    public Boolean QueryAd() throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("select * from administrator where name=?and password=?");
        preparedStatement.setString(1,name.getText());
        preparedStatement.setString(2,password.getText());
        ResultSet resultSet =preparedStatement.executeQuery();//Java类集合框架
        if(resultSet.next()){
            return true;
        }
        else
            return false;
    }

    /**
     * 查询顾客的账号密码是否正确
     * @return 如果账号密码正确则返回true，反之返回false
     * @throws SQLException
     */
    public Boolean QueryCu() throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("select * from customer where customName=?and customPassword=?");
        preparedStatement.setString(1,name.getText());
        preparedStatement.setString(2,password.getText());
        ResultSet resultSet =preparedStatement.executeQuery();//Java类集合框架
        if(resultSet.next()){
            return true;
        }
        else
            return false;
    }
}
