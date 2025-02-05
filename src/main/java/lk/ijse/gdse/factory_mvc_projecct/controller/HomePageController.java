package lk.ijse.gdse.factory_mvc_projecct.controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class HomePageController {

    @FXML
    private Button ButtLogOut;


    @FXML
    private Button ButtPayment;

    @FXML
    private Button ButtWorkSheet;

    @FXML
    private Pane OptionPane;

    @FXML
    private Button buttAtten;

    @FXML
    private Button buttBranch;

    @FXML
    private Button buttDililvary;

    @FXML
    private Button buttMachine;

    @FXML
    private Button buttProductDetail;

    @FXML
    private Button buttProduct;

    @FXML
    private Button buttSalary;

    @FXML
    private Button buttStock;

    @FXML
    private Button buttSupplier;

    @FXML
    private Button buutEmployee;

    @FXML
    private AnchorPane homePageAnchorPane;

    private AnchorPane LoginAnchorPane;

    @FXML
    private Label lblHomePage;

    @FXML
    private AnchorPane loadingAnchorPane;

    LoginViewController loginViewController = new LoginViewController();


    @FXML
    void logoutOnAction(ActionEvent event) throws IOException {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Log out this Programme?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = true;
                if (isDeleted) {
                    homePageAnchorPane.getChildren().clear();
                    AnchorPane load = FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
                    homePageAnchorPane.getChildren().add(load);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Log out not Programme").show();
                }
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "not found").show();

        }
    }

    @FXML
    void attendenceOnAction(ActionEvent event){
        navigateTo("/View/AttendenceView.fxml");
    }

    @FXML
    void branchOnAction(ActionEvent event) {
        navigateTo("/View/BranchView.fxml");
    }

    @FXML
    void employeeOnAction(ActionEvent event) {
        navigateTo("/View/EmployeeView.fxml");
    }

    @FXML
    void machineOnAction(ActionEvent event){
        navigateTo("/View/MachinesView.fxml");
    }

    @FXML
    void paymentOnAction(ActionEvent event) {
        navigateTo("/View/PaymentView.fxml");
    }

    @FXML
    void productOnAction(ActionEvent event) {
        navigateTo("/View/ProductView.fxml");
    }

    @FXML
    void productDetailOnAction(ActionEvent event) {

    }

    @FXML
    void salaryOnAction(ActionEvent event){
        navigateTo("/View/SalaryView.fxml");

    }

    @FXML
    void stockOnAction(ActionEvent event)  {
        navigateTo("/View/StockView.fxml");

    }

    @FXML
    void supplierOnAction(ActionEvent event){
        navigateTo("/View/SupplierView.fxml");

    }

    @FXML
    void worksheetOnAction(ActionEvent event)  {
        navigateTo("/View/Worksheet.fxml");

    }


    private void navigateTo(String fxmlPath) {
        try {
            loadingAnchorPane.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane loadedPane = loader.load();
            loadingAnchorPane.getChildren().add(loadedPane);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load view: " + fxmlPath);
        }
        System.out.println("Loading FXML: " + fxmlPath);
    }
}

