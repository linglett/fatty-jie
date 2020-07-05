package controller;

import driver.MySQLDrive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import dao.getCommodity;
import dao.merch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class CommodityController implements Initializable {
    @FXML
    private Label tips;
    @FXML
    private Button food;
    @FXML
    private Button daily;
    @FXML
    private Button stationery;
    @FXML
    private Button home;
    @FXML
    private Button cloth;
    @FXML
    private Button drink;
    @FXML
    private Button fresh;
    @FXML
    private Button product;
    @FXML
    private Button return1;
    @FXML
    private Label num;
    @FXML
    private Label name;
    @FXML
    private Label made;
    @FXML
    private Label type1;
    @FXML
    private Pane buypane;
    @FXML
    private Label error1;
    @FXML
    private ListView productionList;

    private String type = "日用品类";
    private merch nMerch;
    public static String custom;
    URL url = new URL("file:/D:/idea%e9%a1%b9%e7%9b%ae/SupermarketProject/out/production/SupermarketProject/fxml/commodityScreen.fxml");


    private ObservableList<merch> productionObsList = FXCollections.observableArrayList();

    public CommodityController() throws MalformedURLException {
    }

    /**
     * 点击食品类按钮，显示食品类的商品
     */
    public void clickFood() {
        enterInterface();
        type = "食品类";
        initialize(url, null);
    }

    /**
     * 点击日用品类按钮，显示日用品类的商品
     */
    public void clickDaily() {
        enterInterface();
        type = "日用品类";
        initialize(url, null);
    }

    /**
     * 点击文具类按钮，显示文具类的商品
     */
    public void clickStationery() {
        enterInterface();
        type = "文具类";
        initialize(url, null);
    }

    /**
     * 点击家居类按钮，显示家居类的商品
     */
    public void clickHome() {
        enterInterface();
        type = "家居类";
        initialize(url, null);
    }

    /**
     * 点击服饰类按钮，显示服饰类的商品
     */
    public void clickCloth() {
        enterInterface();
        type = "服饰类";
        initialize(url, null);
    }

    /**
     * 点击饮品类按钮，显示饮品类的商品
     */
    public void clickDrink() {
        enterInterface();
        type = "饮品类";
        initialize(url, null);
    }

    /**
     * 点击生鲜类按钮，显示生鲜类的商品
     */
    public void clickFresh() {
        enterInterface();
        type = "生鲜类";
        initialize(url, null);
    }

    /**
     * 点击数码产品类按钮，显示数码产品类的商品
     */
    public void clickProduct() {
        enterInterface();
        type = "数码产品类";
        initialize(url, null);
    }

    /**
     * 点击返回按钮，返回商品类型表
     */
    public void clickReturn() {
        returnInterface();
    }

    /**
     * 进入选择商品界面，退出商品类型界面
     */
    public void enterInterface() {
        food.setVisible(false);
        cloth.setVisible(false);
        stationery.setVisible(false);
        daily.setVisible(false);
        drink.setVisible(false);
        fresh.setVisible(false);
        product.setVisible(false);
        home.setVisible(false);
        productionList.setVisible(true);
        return1.setVisible(true);

        tips.setText("请选择您的商品");
    }

    /**
     * 退出选择商品界面，进入商品类型界面
     */
    public void returnInterface() {
        food.setVisible(true);
        cloth.setVisible(true);
        stationery.setVisible(true);
        daily.setVisible(true);
        drink.setVisible(true);
        fresh.setVisible(true);
        product.setVisible(true);
        home.setVisible(true);
        productionList.setVisible(false);
        return1.setVisible(false);

        tips.setText("请选择您要购买的商品类型");
    }

    /**
     * 点击购买，商品库存减一，保存购买记录，刷新界面
     *
     * @throws SQLException
     * @throws MalformedURLException
     */
    public void clicktobuy() throws SQLException, MalformedURLException {
        if (nMerch.stockNum != 0) {
            buyIt(nMerch);
            userBuy();
            buypane.setVisible(false);
            //刷新
            URL url = new URL("file:/D:/idea%e9%a1%b9%e7%9b%ae/SupermarketProject/out/production/SupermarketProject/fxml/commodityScreen.fxml");
            initialize(url, null);
        } else {
            error1.setVisible(true);
        }
    }

    /**
     * 点击返回选择商品界面，退出购买界面
     */
    public void clicktoreturn() {
        buypane.setVisible(false);
    }

    /**
     * 初始化
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            productionObsList = new getCommodity().getCommodities(type);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productionList.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell<merch> listCell = new ListCell<merch>() {
                    protected void updateItem(merch item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox b1 = new HBox(50);
                            b1.setPrefHeight(50.0);
                            Label l1 = new Label();
                            l1.setPrefSize(1000, 1000);
                            l1.setText(item.merchName + "    售价：" + item.merchPrice + "    生产日期：" + item.ProductionDate + "    产地：" + item.madeIn + "    剩余库存：" + item.stockNum);
                            b1.getChildren().addAll(l1);
                            setGraphic(b1);
                            if (item.stockNum != 0) {
                                //点击购买
                                b1.setOnMouseClicked(new EventHandler() {
                                    @Override
                                    public void handle(Event event) {
                                        nMerch = item;
                                        name.setText(nMerch.merchName);
                                        num.setText(String.valueOf(nMerch.stockNum));
                                        made.setText(nMerch.madeIn);
                                        type1.setText(nMerch.merchType);
                                        buypane.setVisible(true);
                                    }
                                });
                            }
                        } else {
                            setText(null);
                            setGraphic(null);
                        }
                    }
                };
                return listCell;
            }
        });
        productionList.setItems(productionObsList);
    }

    /**
     * 用户点击购买  貨物库存减一
     *
     * @param it
     * @throws SQLException
     */
    public void buyIt(merch it) throws SQLException {
        PreparedStatement ChangeUser1 = MySQLDrive.GetPreparedStatement("update merch set stockNum = ? where merchId= ?");
        ChangeUser1.setInt(1, it.getStockNum() - 1);
        ChangeUser1.setString(2, it.merchId);//param代表第几个问号
        ChangeUser1.executeUpdate();
        System.out.println("商品-1");
    }

    /**
     * 点击购买，存储顾客购买的记录入数据库
     *
     * @throws SQLException
     */
    public void userBuy() throws SQLException {
        PreparedStatement preparedStatement = MySQLDrive.GetPreparedStatement("insert into buy values(?,?,?,?)");
        preparedStatement.setString(1, custom);
        preparedStatement.setString(2, nMerch.merchName);
        preparedStatement.setString(3, getTime());
        preparedStatement.setString(4, nMerch.madeIn);
        preparedStatement.execute();
        System.out.println("购买成功");
    }

    /**
     * 得到当前时间
     *
     * @return 返回顾客购买商品的时间
     */
    public static String getTime() {
        Date TimeOfNow = new Date();
        SimpleDateFormat toString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time;
        time = toString.format(TimeOfNow);
        return time;
    }

    /**
     * 把商品信息读入文件中
     * @param path 文件路径
     * @param txt 写入文件的信息
     * @return
     */
    public static boolean writeFile(String path, ArrayList<merch> txt) {
        for(int i=0;i<txt.size();i++){
            System.out.println(txt.size());
            System.out.println(txt.get(i).merchName);
        }

        File file = new File(path);
        try {
            if(file.exists()){
                System.out.println("文件已经存在！");
            }
            else{
                System.out.println("文件不存在，新建文件...");
                file.createNewFile();
            }

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));//直接覆盖

            oos.writeObject(txt);
            oos.flush();
            oos.close();
            System.out.println("保存入文件成功");
            return true;
        } catch (IOException e) {
            System.out.println("存入文件失败...");
            e.printStackTrace();
            return false;
        }
    }

    /*
    **读取文件
    public static ArrayList<merch> test(String path) {
        String pathName = path;
        ArrayList<merch> merchArrayList;
        File fileName = new File(pathName);
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            merchArrayList =(ArrayList<merch>)  ois.readObject();
            ois.close();
            System.out.println("读取文件成功!");
            return merchArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取文件失败！");
            return null;
        }
    }*/
}
