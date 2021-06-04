package controller;

import dao.CompanyDao;
import dao.impl.AssetDaoImpl;
import dao.impl.CompanyDaoImpl;
import domain.AssetInfo;
import domain.Company;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Edit implements Initializable {
    @FXML
    public ChoiceBox companyList;
    @FXML
    public ChoiceBox assetList;
    @FXML
    public Label credit;
    @FXML
    public Label quantity;
    @FXML
    public TextField changeCredit;
    @FXML
    public TextField changeQuantity;

    /**
     * 弹框
     */
    public void alert(String title, String content, String header, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Company> companylist = new CompanyDaoImpl().findAll();
        ObservableList<String> data = FXCollections.observableArrayList();
        for (Company company : companylist) {
            data.add(company.getCompanyName());
        }
        for (String datum : data) {
            companyList.getItems().add(datum);
        }

        companyList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Object value = companyList.getValue();
                Company company = new CompanyDaoImpl().findByName((String) value);
                credit.setText(String.valueOf(company.getCredit()));
                List<AssetInfo> assetList1 = new AssetDaoImpl().findAssetByCompany(company.getCompanyName());
                ObservableList<String> data1 = FXCollections.observableArrayList();
                for (AssetInfo assetInfo : assetList1) {
                    data1.add(assetInfo.getAssetName());
                }
                if (assetList.getItems()!=null){
                    assetList.getItems().clear();
                }
                    quantity.setText(" ");
                for (String s : data1) {
                    assetList.getItems().add(s);
                }
                assetList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Object value1 = assetList.getValue();
                        AssetInfo asset = new AssetDaoImpl().findAssetByNameAndCompany((String) value1, (String) value);
                        if (asset!=null){
                            quantity.setText(asset.getQuantity());
                        }
                    }
                });
            }
        });
    }

    public void doChange(ActionEvent actionEvent) {
        String text = changeCredit.getText();
        String text1 = changeQuantity.getText();
        Object value = companyList.getValue();
        Object value1 = assetList.getValue();
//        System.out.println(text+" "+text1+" "+value+" "+value1);
        if (text.equals("") && text1.equals("")){
            alert("ERROR", "There is no data to modify! Please enter after confirmation!", null, Alert.AlertType.ERROR);
        }
        else if (text1.equals("")){
            if (text.equals(credit.getText())){
                alert("ERROR", "Please modify the value!", null, Alert.AlertType.ERROR);
            }
            new CompanyDaoImpl().updateCredit((String)value,text);
            alert("SUCCESS", "Successfully modified!", null, Alert.AlertType.INFORMATION);
            credit.setText(text);
            changeCredit.clear();
        }
        else if (text.equals("")){
            if (text1.equals(credit.getText())){
                alert("ERROR", "Please modify the value!", null, Alert.AlertType.ERROR);
            }
            new AssetDaoImpl().updateQuantity((String)value,(String)value1,text1);
            alert("SUCCESS", "Successfully modified!", null, Alert.AlertType.INFORMATION);
            quantity.setText(text1);
            changeQuantity.clear();
        }
        else {
            if (text.equals(credit.getText())){
                alert("ERROR", "Please modify the value!", null, Alert.AlertType.ERROR);
            }
            if (text1.equals(credit.getText())){
                alert("ERROR", "Please modify the value!", null, Alert.AlertType.ERROR);
            }
            new CompanyDaoImpl().updateCredit((String)value,text);
            new AssetDaoImpl().updateQuantity((String)value,(String)value1,text1);
            alert("SUCCESS", "Successfully modified!", null, Alert.AlertType.INFORMATION);
            credit.setText(text);
            quantity.setText(text1);
            changeCredit.clear();
            changeQuantity.clear();
        }
    }
}
