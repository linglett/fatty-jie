package dao;

import driver.MySQLDrive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getBuyInformation {
    public ObservableList<buy> getInformation() throws SQLException, IOException {

        ObservableList<buy> ObsList = FXCollections.observableArrayList();
        Statement stmt = MySQLDrive.GetStatement();
        ResultSet rs = stmt.executeQuery("select *from buy");

        while (rs.next()) {
            buy C1 = new buy(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            ObsList.add(C1);
        }
        return ObsList;
    }
}
