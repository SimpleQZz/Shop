package controller;

import dao.impl.UserDaoImpl;
import domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Forget {
    @FXML
    public TextField username;
    @FXML
    public TextField companyName;
    @FXML
    public TextField password;
    @FXML
    public TextField cpassword;
    @FXML
    public Button button;

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


    public void confirm(ActionEvent actionEvent) {
        String usernameText = username.getText();
        String companyNameText = companyName.getText();
        String pwd = password.getText();
        String cpwd = cpassword.getText();

        User user=new UserDaoImpl().findByUserName(usernameText);
        if (user==null){
            alert("ERROR", "The user does not exist, please re-enter!", null, Alert.AlertType.ERROR);
        }else if (!companyNameText.equals(user.getCompanyName())){
            alert("ERROR", "The company of the user name is wrong, please confirm and input!", null, Alert.AlertType.ERROR);
        }else if (pwd.isEmpty()){
            alert("ERROR", "Password is empty, please re-enter", null, Alert.AlertType.ERROR);
        }else if (!pwd.equals(cpwd)){
            alert("ERROR", "The two passwords are inconsistent, please re-enter!", null, Alert.AlertType.ERROR);
        }else{
            new UserDaoImpl().updateUser(usernameText,pwd);
            alert("SUCCESS", "Successfully modified!", null, Alert.AlertType.INFORMATION);
            Stage primaryStage = (Stage) button.getScene().getWindow();
            primaryStage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage dh = new Stage();//新建Stage
            Scene scene = new Scene(root);
            dh.setScene(scene);
            dh.show();//打开新的窗口
        }
    }
}
