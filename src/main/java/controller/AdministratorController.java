package controller;

import driver.MySQLDrive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import dao.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdministratorController implements Initializable {
    @FXML
    private TextField selectmarch;
    @FXML
    private Button selectmarchbutton;
    @FXML
    private Pane welcome;
    @FXML
    private Pane purchase;
    @FXML
    private Pane change1;
    @FXML
    private Pane change2;
    @FXML
    private TextField MerchName;
    @FXML
    private TextField MerchID;
    @FXML
    private TextField MerchNum;
    @FXML
    private TextField MerchPrice;
    @FXML
    private TextField MerchMade;
    @FXML
    private ListView QuerySystem;
    @FXML
    private DatePicker MerchDate;
    @FXML
    private TextField MerchType;
    @FXML
    private TextField MerchName1;
    @FXML
    private TextField MerchID1;
    @FXML
    private TextField MerchNum1;
    @FXML
    private TextField MerchPrice1;
    @FXML
    private TextField MerchMade1;
    @FXML
    private DatePicker MerchDate1;
    @FXML
    private TextField MerchType1;
    @FXML
    private ListView customerList;
    @FXML
    private Pane list;
    @FXML
    private Pane operation;
    @FXML
    private Label labelInventory;
    public merch newMerchs;//获取的点击的商品

    public ArrayList<merch> merchesList = new ArrayList<>();

    private ObservableList<merch> merchObsList = FXCollections.observableArrayList();
    private ObservableList<buy> buyObservableList = FXCollections.observableArrayList();
    private  ObservableList<merch> AllMerchObsList;
    /**
     * 点击展示顾客表
     * @param event
     */
    @FXML
    public void clickCustom(MouseEvent event){
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
            welcome.setVisible(false);
            list.setVisible(true);
        }
    }

    /**
     * 搜索商品的数据库操作
     */
    public void selectMarch(){
        merchObsList.clear();
        try {
            PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("select * from merch where merchId=? OR merchname=?");
            preparedStatement.setString(1,selectmarch.getText());
            preparedStatement.setString(2,selectmarch.getText());
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next()){
                merch merch =new merch(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
                merchObsList.add(merch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        QuerySystem.setItems(merchObsList);
    }

    /**
     * 点击商品表事件
     */
    public void listViewClick(){
        merch merch = getMerch(QuerySystem,merchObsList);
        newMerchs=merch;
        labelInventory.setText("商品编号为 "+merch.merchId+" "+"库存为"+merch.stockNum);
        operation.setVisible(true);
    }

    /**
     * 获取点击的merch
     * @param C1 商品表
     * @param C2 商品表ObservableList
     * @return
     */
    public merch getMerch(ListView C1, ObservableList<merch> C2) {
        int merch_index = C1.getSelectionModel().getSelectedIndex();
        if (merch_index >= 0) {
            merch friend_flag = C2.get(merch_index);
            return friend_flag;
        }
        return null;
    }

    /**
     * 点击查询商品按钮
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void clickQueryProducts(MouseEvent event) throws IOException, SQLException {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
            list.setVisible(false);
            change1.setVisible(false);
            change2.setVisible(false);
            welcome.setVisible(false);
            purchase.setVisible(true);
        }
        merchObsList = new getCommodity().getCommodities();
        QuerySystem.setItems(merchObsList);
    }

    /**
     * 对单一商品修改确定
     */
    public void operationOk(){
        operation.setVisible(false);
    }
    public void soldOut() throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("update merch set stockNum = ? where merchId = ?");
        preparedStatement.setInt(1,0);
        preparedStatement.setString(2,newMerchs.merchId);
        preparedStatement.execute();
    }

    /**
     * 点击修改商品按钮事件
     * @param event
     */
    @FXML
    public void clickModifyProduct(MouseEvent event){
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1){
            welcome.setVisible(false);
            purchase.setVisible(true);
        }
    }

    /**
     * 使得库存+1
     * @throws SQLException
     */
    public void stock() throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("update merch set stockNum = ? where merchId = ?");
        preparedStatement.setInt(1,newMerchs.stockNum+1);
        preparedStatement.setString(2,newMerchs.merchId);
        preparedStatement.execute();
    }

    /**
     * 修改取消，修改页面关闭
     */
    public  void changeDown(){
        change2.setVisible(false);
    }

    /**
     * 初始化修改页面
     */
    public void changeMerch(){
        change2.setVisible(true);
        MerchName1.setText(newMerchs.merchName);
        MerchID1.setText(newMerchs.merchId);
        MerchMade1.setText(newMerchs.madeIn);
        MerchNum1.setText(String.valueOf(newMerchs.stockNum) );
        MerchPrice1.setText(String.valueOf(newMerchs.merchPrice));
        MerchType1.setText(newMerchs.merchType);
    }

    /**
     * 保存修改
     * @throws SQLException
     */
    public void changeSave() throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("update merch set merchId = ?,merchName = ?,merchPrice = ?,productionDate = ?,madeln = ?,stockNum = ?,merchType = ? where merchId = ?");
        preparedStatement.setString(1,MerchID1.getText());
        preparedStatement.setString(2,MerchName1.getText());
        preparedStatement.setInt(6,Integer.parseInt(MerchNum1.getText()));
        preparedStatement.setDouble(3,Double.parseDouble(MerchPrice1.getText()));
        preparedStatement.setString(5,MerchMade1.getText());
        preparedStatement.setString(4,MerchDate1.getValue().toString());
        preparedStatement.setString(7,MerchType1.getText());
        preparedStatement.setString(8,newMerchs.merchId);
        preparedStatement.execute();
        change2.setVisible(false);
    }

    /**
     * 初始化页面
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            merchObsList = new getCommodity().getCommodities();
            buyObservableList = new getBuyInformation().getInformation();
            AllMerchObsList=merchObsList;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        QuerySystem.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell<merch> listCell = new ListCell<merch>() {
                    protected void updateItem(merch item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox b1 = new HBox(50);
                            b1.setPrefHeight(50.0);
                            Label l1 = new Label();
                            l1.setPrefSize(1000,1000);
                            l1.setText(item.merchName+"    售价："+item.merchPrice+"    生产日期："+item.ProductionDate+"    产地："+item.madeIn+"    剩余库存："+item.stockNum);
                            b1.getChildren().addAll(l1);
                            setGraphic(b1);
                            //点击修改
                            b1.setOnMouseClicked(new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    newMerchs = item;
                                }
                            });
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                return listCell;
            }
        });
        QuerySystem.setItems(merchObsList);

        customerList.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell<buy> listCell = new ListCell<buy>() {
                    protected void updateItem(buy item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox b1 = new HBox(50);
                            b1.setPrefHeight(50.0);
                            Label l1 = new Label();
                            l1.setPrefSize(1000,1000);
                            l1.setText("用户名称： "+item.userId+"    购买商品名称："+item.buyName+"    购买商品产地：："+item.buyMade+"    购买时间："+item.buyTime);
                            b1.getChildren().addAll(l1);
                            setGraphic(b1);
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                return listCell;
            }
        });
        customerList.setItems(buyObservableList);
    }

    /**
     * 添加信息表点击确认
     * @throws SQLException
     */
    public void clickIt() throws SQLException {
        change1.setVisible(false);
        save(newMerchs);
        QuerySystem.setVisible(true);
    }

    /**
     * 点击添加商品，打开添加页面
     */
    public void clickAdd(){
        change1.setVisible(true);
        MerchName.setText("");
        MerchID.setText("");
        MerchMade.setText("");
        MerchNum.setText("" );
        MerchPrice.setText("");
        MerchType.setText("");
    }

    /**
     * 刷新页面
     * @throws MalformedURLException
     */
    public void clickFlush() throws MalformedURLException {
        URL url = new URL("file:/D:/idea%e9%a1%b9%e7%9b%ae/SupermarketProject/out/production/SupermarketProject/fxml/Administractor.fxml");
        initialize(url,null);
    }

    /**
     * 保存新添加的商品
     * @param newUser
     * @throws SQLException
     */
    public void save(merch newUser) throws SQLException {
        //数据库存储
        PreparedStatement  C1= MySQLDrive.GetPreparedStatement("insert into merch values(?,?,?,?,?,?,?)");
        C1.setString(1,MerchID.getText());
        C1.setString(2,MerchName.getText());
        C1.setInt(6,Integer.parseInt(MerchNum.getText()));
        C1.setDouble(3,Double.parseDouble(MerchPrice.getText()));
        C1.setString(5,MerchMade.getText());
        C1.setString(4,MerchDate.getValue().toString());
        C1.setString(7,MerchType.getText());
        C1.executeUpdate();
        QuerySystem.setVisible(true);
    }

    /**
     * 点击保存到本地
     */
    public void clickPreserve(){
        String path = "src/商品信息.txt";
        for (merch merch : AllMerchObsList) {
            merchesList.add(merch);
        }
        CommodityController.writeFile(path,merchesList);
    }

    public void change1Close(){
        change1.setVisible(false);
    }
}