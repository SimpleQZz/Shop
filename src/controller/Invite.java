package controller;

import dao.impl.AdministratorDaoImpl;
import domain.Administrator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class Invite {
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField cpassword;
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

    public void add(ActionEvent actionEvent) {
        List<Administrator> list = new AdministratorDaoImpl().findAll();
        if (!password.getText().equals(cpassword.getText())) {
            alert("ERROR", "The password input is inconsistent, please re-enter!", null, Alert.AlertType.ERROR);
        } else {
            for (Administrator administrator : list) {
                if (username.getText().equals(administrator.getAdname())) {
                    alert("ERROR", "Username already exists, please re-enter!", null, Alert.AlertType.ERROR);
                    return;
                }
            }
            Administrator administrator = new Administrator(null, username.getText(), password.getText());
            new AdministratorDaoImpl().save(administrator);
            alert("SUCCESS", "added Successfully!", null, Alert.AlertType.INFORMATION);
            // TODO: 2021/5/27 页面跳转
        }
    }
}
