package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private StackPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("../view/create.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(fxml);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void create(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/create.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void invite(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/invite.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void assets(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/assets.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
    public void edit(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("../view/edit.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }
}