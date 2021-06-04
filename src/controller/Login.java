package controller;


import dao.impl.AdministratorDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Administrator;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import viewModel.SourceDataViewModel;

import java.io.IOException;

public class Login {
    @FXML
    public javafx.scene.control.TextField username;
    @FXML
    public javafx.scene.control.Button confirm;
    @FXML
    private PasswordField password;

    private SourceDataViewModel viewModel = SourceDataViewModel.getInstance();

    public void initialize(){
        viewModel.nameProperty().bindBidirectional(username.textProperty());
    }

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
    public void login() throws IOException {
        User user = new UserDaoImpl().findUser(username.getText(), password.getText());
        if (user != null) {
            Stage primaryStage = (Stage) confirm.getScene().getWindow();
            primaryStage.close();
            viewModel.setTargetData();
            Parent root = FXMLLoader.load(getClass().getResource("../view/cartlist.fxml"));
            Stage dh = new Stage();//新建Stage
            Scene scene = new Scene(root);
            dh.setScene(scene);
            dh.show();//打开新的窗口
        }
        Administrator administrator = new AdministratorDaoImpl().findOne(username.getText(), password.getText());
        if (administrator!=null){
            Stage primaryStage = (Stage) confirm.getScene().getWindow();
            primaryStage.close();
            Parent root = FXMLLoader.load(getClass().getResource("../view/admin.fxml"));
            Stage dh = new Stage();//新建Stage
            Scene scene = new Scene(root);
            dh.setScene(scene);
            dh.show();//打开新的窗口
        }

        if (user==null&&administrator==null){
            alert("ERROR", "The username or password is wrong, please re-enter!", null, Alert.AlertType.ERROR);
        }

    }

    public void forget(ActionEvent actionEvent) {
        Stage primaryStage = (Stage) confirm.getScene().getWindow();
        primaryStage.close();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/forget.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage dh = new Stage();//新建Stage
        Scene scene = new Scene(root);
        dh.setScene(scene);
        dh.show();//打开新的窗口
    }
}
