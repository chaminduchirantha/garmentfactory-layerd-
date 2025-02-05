package lk.ijse.gdse.factory_mvc_projecct.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginViewController {

    @FXML
    AnchorPane LoginAnchorPane;

    @FXML
    private Pane userNamePasswordAnchorePane;

    @FXML
    private Button loginBut;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;


    @FXML
    void loginOnAction(ActionEvent event) throws IOException {
        try {
            String username = txtName.getText();
            String password = txtPassword.getText();

            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
            txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String passwordPattern = "^[A-Za-z0-9]+$";

            boolean isValidName = username.matches(namePattern);
            boolean isValidPassword = password.matches(passwordPattern);

            if (!isValidName) {
                System.out.println(txtName.getStyle());
                txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidPassword) {
                System.out.println(txtPassword.getStyle());
                txtPassword.setStyle(txtPassword.getStyle() + ";-fx-border-color: red;");
            }

            if (isValidName && isValidPassword) {

                if (username.equals("user") && password.equals("1234")) {
                    LoginAnchorPane.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
                    LoginAnchorPane.getChildren().add(load);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid username or password ").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "LoginPage Not Found").show();
        }
    }
}
