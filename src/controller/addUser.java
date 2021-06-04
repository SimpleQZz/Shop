package controller;

import dao.impl.CompanyDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Company;
import domain.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ResourceBundle;

public class addUser implements Initializable {
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField cpassword;
    @FXML
    public ChoiceBox companyList;
    @FXML
    public Button upload;

    private final Desktop desktop = Desktop.getDesktop();

    /**
     * 弹框
     */
    public void alert(String title, String content, String header, Alert.AlertType type) {
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
    }

    public void add(ActionEvent actionEvent) {
        List<User> list = new UserDaoImpl().findAll();
        Object value = companyList.getValue();
        if (!password.getText().equals(cpassword.getText())) {
            alert("ERROR", "The password input is inconsistent, please re-enter!", null, Alert.AlertType.ERROR);
        } else {
            for (User user : list) {
                if (username.getText().equals(user.getUsername())) {
                    alert("ERROR", "Username already exists, please re-enter!", null, Alert.AlertType.ERROR);
                    return;
                }
            }
            User user = new User(null, username.getText(), password.getText(), String.valueOf(value));
            new UserDaoImpl().save(user);
            alert("SUCCESS", "added Successfully!", null, Alert.AlertType.INFORMATION);
        }
    }

    public void add(User user) {
        List<User> list = new UserDaoImpl().findAll();
        for (User user1 : list) {
            if (user1.getUsername().equals(user.getUsername())) {
                alert("ERROR", "Username already exists, please re-enter!", null, Alert.AlertType.ERROR);
                return;
            }
        }
        new UserDaoImpl().save(user);
        alert("SUCCESS", "added Successfully!", null, Alert.AlertType.INFORMATION);
    }

    public void upload(ActionEvent actionEvent) throws IOException {
        if (companyList.getValue()==null){
            alert("ERROR", "The company cannot be empty! Please select a company!",null, Alert.AlertType.ERROR);
            return;
        }
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog((Stage) upload.getScene().getWindow());
        String path = file.getPath();
        String str = path.trim();
        String s = "";
        int index = str.lastIndexOf(".");
        if (index > 0 && index < str.length() - 1) {
            s = str.substring(index + 1);
        }
        if (s.equals("txt")) {
            Object value = companyList.getValue();
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8);// 考虑到编码格式
            BufferedReader br = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                try {
                    String[] split = lineTxt.split(",");
                    User user = new User(null, split[0], split[1], String.valueOf(value));
                    List<User> list = new UserDaoImpl().findAll();
                    for (User user1 : list) {
                        if (user1.getUsername().equals(user.getUsername())) {
                            alert("ERROR", "Username already exists, please re-enter!", null, Alert.AlertType.ERROR);
                            return;
                        }
                    }
                    new UserDaoImpl().save(user);
                } catch (Exception e) {
                    alert("ERROR", "The text format is wrong! Please standardize the format!", null, Alert.AlertType.ERROR);
                    return;
                }
            }
            alert("SUCCESS", "added Successfully!", null, Alert.AlertType.INFORMATION);
            br.close();
            read.close();
        } else {
            alert("ERROR", "The file format is wrong! Please select again!", null, Alert.AlertType.ERROR);
        }
    }

}

