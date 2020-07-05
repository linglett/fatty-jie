package dao;

import java.io.Serializable;

public class merch implements Serializable {
    public String merchId;
    public String merchName;
    public double merchPrice;
    public String ProductionDate;
    public String madeIn;
    public int stockNum;
    public String merchType;


    public String getMerchId() {
        return merchId;
    }

    public void setMerchId(String merchId) {
        this.merchId = merchId;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public double getMerchPrice() {
        return merchPrice;
    }

    public void setMerchPrice(double merchPrice) {
        this.merchPrice = merchPrice;
    }

    public String getProductionDate() {
        return ProductionDate;
    }

    public void setProductionDate(String productionDate) {
        ProductionDate = productionDate;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public String getMerchType() {
        return merchType;
    }

    public void setMerchType(String merchType) {
        this.merchType = merchType;
    }

    public merch(String merchId, String merchName, double merchPrice, String productionDate, String madeIn, int stockNum, String merchType) {
        this.merchId = merchId;
        this.merchName = merchName;
        this.merchPrice = merchPrice;
        this.ProductionDate = productionDate;
        this.madeIn = madeIn;
        this.stockNum = stockNum;
        this.merchType = merchType;
    }
}
