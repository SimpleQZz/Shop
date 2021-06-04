package domain;

import javafx.beans.property.SimpleStringProperty;

public class AssetSelling {
    private final SimpleStringProperty AssetName;
    private final SimpleStringProperty RemainNum;
    private final SimpleStringProperty uploadTime;
    private final SimpleStringProperty state;


    public AssetSelling(String  assetName, String remainNum, String uploadTime, String state) {
        this.AssetName = new SimpleStringProperty(assetName);
        this.RemainNum = new SimpleStringProperty(remainNum);
        this.uploadTime =new SimpleStringProperty(uploadTime);
        this.state = new SimpleStringProperty(state);
    }

    public String getAssetName() {
        return AssetName.get();
    }

    public SimpleStringProperty assetNameProperty() {
        return AssetName;
    }

    public void setAssetName(String assetName) {
        this.AssetName.set(assetName);
    }

    public String getRemainNum() {
        return RemainNum.get();
    }

    public SimpleStringProperty remainNumProperty() {
        return RemainNum;
    }

    public void setRemainNum(String remainNum) {
        this.RemainNum.set(remainNum);
    }

    public String getUploadTime() {
        return uploadTime.get();
    }

    public SimpleStringProperty uploadTimeProperty() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime.set(uploadTime);
    }

    public String getState() {
        return state.get();
    }

    public SimpleStringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    @Override
    public String toString() {
        return "AssetInfo{" +
                "AssetName=" + AssetName +
                ", RemainNum=" + RemainNum +
                ", uploadTime=" + uploadTime +
                ", state=" + state +
                '}';
    }
}
