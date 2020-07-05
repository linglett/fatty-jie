package dao;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class getMerchList {
    public ArrayList<merch> getMerches() throws SQLException, IOException {

        ObservableList<merch> ObsList;
        ObsList = new getCommodity().getCommodities();
        ArrayList<merch> merches = new ArrayList<>();

        for(int i=0;i<ObsList.size();i++)
        {
            merches.add(i,ObsList.get(i));
        }
        return merches;
    }
}
