package controller;

import dao.impl.AssetDaoImpl;
import domain.AssetInfo;
import domain.AssetSelling;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Assets implements Initializable {
    @FXML
    public TableColumn nameCol;
    @FXML
    public TableColumn remainCol;
    @FXML
    public TableColumn uploadCol;
    @FXML
    public TableColumn stateCol;
    @FXML
    public TableView assettableview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("assetName"));
        remainCol.setCellValueFactory(new PropertyValueFactory<>("remainNum"));
        uploadCol.setCellValueFactory(new PropertyValueFactory<>("uploadTime"));
        stateCol.setCellValueFactory(new PropertyValueFactory<>("state"));

        List<AssetSelling> list = new AssetDaoImpl().findAll();
        ObservableList<AssetSelling> data = FXCollections.observableArrayList();
        for (AssetSelling assetSelling : list) {
            data.add(assetSelling);
        }
        assettableview.setItems(data);

    }

}
