package domain;

import javafx.beans.property.SimpleStringProperty;

public class AssetInfo {
    private final SimpleStringProperty assetName;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty updateTime;


    public AssetInfo(String  assetName, String quantity, String companyName, String updateTime) {
        this.assetName = new SimpleStringProperty(assetName);
        this.quantity = new SimpleStringProperty(quantity);
        this.companyName =new SimpleStringProperty(companyName);
        this.updateTime = new SimpleStringProperty(updateTime);
    }

    public String getAssetName() {
        return assetName.get();
    }

    public SimpleStringProperty assetNameProperty() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName.set(assetName);
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public SimpleStringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getUpdateTime() {
        return updateTime.get();
    }

    public SimpleStringProperty updateTimeProperty() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime.set(updateTime);
    }

    @Override
    public String toString() {
        return "AssetInfo{" +
                "assetName=" + assetName +
                ", quantity=" + quantity +
                ", companyName=" + companyName +
                ", updateTime=" + updateTime +
                '}';
    }
}
