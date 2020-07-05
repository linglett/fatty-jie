package dao;

import driver.MySQLDrive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getCommodity {
    public ObservableList<merch> getCommodities(String type) throws SQLException, IOException {
        ObservableList<merch> ObsList = FXCollections.observableArrayList();
        Statement stmt = MySQLDrive.GetStatement();
        ResultSet rs = stmt.executeQuery("select *from merch");

        while (rs.next()) {
            if (type.equals(rs.getString(7))) {
                merch C1 = new merch(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
                ObsList.add(C1);
            }
        }
        return ObsList;
    }

    public ObservableList<merch> getCommodities() throws SQLException, IOException {

        ObservableList<merch> ObsList = FXCollections.observableArrayList();
        Statement stmt = MySQLDrive.GetStatement();
        ResultSet rs = stmt.executeQuery("select *from merch");

        while (rs.next()) {
            merch C1 = new merch(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
            ObsList.add(C1);
        }
        return ObsList;
    }
}
