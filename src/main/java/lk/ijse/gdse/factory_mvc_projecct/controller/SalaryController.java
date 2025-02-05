package lk.ijse.gdse.factory_mvc_projecct.controller;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse.factory_mvc_projecct.bo.BoFactory;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.SalaryBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.SalaryDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.SalaryTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {



    @FXML
    private Button buttAdd;

    @FXML
    private Button buttClear;

    @FXML
    private Button buttRemove;

    @FXML
    private Button buttUpdate;

    @FXML
    private Label lblBasicSalry;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblFees;

    @FXML
    private Label lblid;

    @FXML
    private AnchorPane salaryAnchorPane;

    @FXML
    private ComboBox<String> cmbPaymentMethod;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private ComboBox<String> cmbEmployeeContactNumber;

    @FXML
    private Label lblEmName;

    @FXML
    private Label lblEmName1;

    @FXML
    private TableColumn<SalaryTm, String> colomnBasicSalary;

    @FXML
    private TableColumn<SalaryTm, String> colomnEmployeeId;

    @FXML
    private TableColumn<SalaryTm, String> colomnId;

    @FXML
    private TableColumn<SalaryTm, String> colomnReleasDate;

    @FXML
    private TableColumn<SalaryTm, String> colomnMethod;

    @FXML
    private TableColumn<SalaryTm, String> colomnSalaryFees;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtEmId;

    @FXML
    private TextField txtFees;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtSalary;

    SalaryBo salaryBo = (SalaryBo) BoFactory.getInstance().getBoType(BoFactory.BoType.SALARY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colomnId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colomnReleasDate.setCellValueFactory(new PropertyValueFactory<>("salaryReleaseDate"));
        colomnSalaryFees.setCellValueFactory(new PropertyValueFactory<>("salaryFees"));
        colomnBasicSalary.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        colomnMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colomnEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(salaryAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadNextSalaryId();
            loadTableData();
            loadEmployeeContactNumber();
            loadNextEmployeeId();
            loadCmb();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadNextSalaryId() {
        try {
            String nextSalaryID = salaryBo.getNextId();
            txtId.setText(nextSalaryID);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    private void loadNextEmployeeId() {
        try {
            String nextEmployeeId = salaryBo.getNextId();

            txtEmId.setText(nextEmployeeId);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    private void loadCmb(){
        String[] methods = {"Cash" , "Bank Account" , "cheque"};
        cmbPaymentMethod.getItems().addAll(methods);

    }


    @FXML
    void addOnAction(ActionEvent event){
        try {
            String salaryId = txtId.getText();
            String salaryFees = txtFees.getText();
            LocalDate salaryReleaseDate = txtDate.getValue();
            String basicSalary = txtSalary.getText();
            String paymentMethod = cmbPaymentMethod.getValue();
            String employeeId = txtEmId.getText();

            SalaryDto salaryDto = new SalaryDto(
                    salaryId ,
                    salaryFees ,
                    salaryReleaseDate ,
                    basicSalary ,
                    paymentMethod,
                    employeeId
            );

            boolean isSaved = salaryBo.save(salaryDto);
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Salary added successfully").show();
                loadTableData();
            }else {
                new Alert(Alert.AlertType.ERROR, "Salary not added successfully").show();
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    private void loadTableData(){
        try {
            ArrayList<SalaryDto>salaries = salaryBo.getAll();

            ObservableList<SalaryTm> salaryTms = FXCollections.observableArrayList();

            for (SalaryDto salaryDto : salaries) {
                SalaryTm salaryTm = new SalaryTm(
                        salaryDto.getSalaryId(),
                        salaryDto.getSalaryFees(),
                        salaryDto.getSalaryReleaseDate(),
                        salaryDto.getBasicSalary(),
                        salaryDto.getPaymentMethod(),
                        salaryDto.getEmployeeId()

                );
                salaryTms.add(salaryTm);
            }
            tblSalary.setItems(salaryTms);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }


    private void refreshPage(){
        loadNextSalaryId();

        buttAdd.setDisable(false);

        buttUpdate.setDisable(true);
        buttRemove.setDisable(true);

        txtFees.setText("");
        txtFees.setText("");
        txtDate.setTooltip(new Tooltip("Date"));
        txtSalary.setText("");
        cmbPaymentMethod.setValue("");
         lblEmName.setText("");
         lblEmId.setText("");
        cmbEmployeeContactNumber.setValue("");
    }

    @FXML
    void clearOnAction(ActionEvent event) {
        try {
                refreshPage();
            } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    @FXML
    void removeOnAction(ActionEvent event){
        try {
            String salaryId = txtId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to delete this salary?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = salaryBo.delete(salaryId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION , "Salary deleted").show();
                    loadTableData();

                }else{
                    new Alert(Alert.AlertType.ERROR , "Salary not deleted").show();
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event){
        try {
            String salaryId = txtId.getText();
            String salaryFees = txtFees.getText();
            LocalDate salaryReleaseDate = txtDate.getValue();
            String basicSalary = txtSalary.getText();
            String paymentMethod = cmbPaymentMethod.getValue();
            String employeeId = txtEmId.getText();

            if (salaryReleaseDate == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a valid salary release date").show();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to Update this salary?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                SalaryDto salaryDto = new SalaryDto(
                        salaryId,
                        salaryFees,
                        salaryReleaseDate,
                        basicSalary,
                        paymentMethod,
                        employeeId
                );

                boolean isUpdated = salaryBo.update(salaryDto);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Salary update successfully").show();
                    loadTableData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Salary not update successfully").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Salary not found").show();
        }
    }

    @FXML
    void salaryOnMouseClicked(MouseEvent event){
        SalaryTm salaryTm = tblSalary.getSelectionModel().getSelectedItem();
        if (salaryTm != null) {
            txtId.setText(salaryTm.getSalaryId());
            txtFees.setText(salaryTm.getSalaryFees());
            txtDate.setValue(salaryTm.getSalaryReleaseDate());
            txtSalary.setText(salaryTm.getBasicSalary());
            cmbPaymentMethod.setValue(salaryTm.getPaymentMethod());
            txtEmId.setText(salaryTm.getEmployeeId());

            buttAdd.setDisable(true);
            buttRemove.setDisable(false);
            buttUpdate.setDisable(false);
            loadTableData();
        }
    }

    private void loadEmployeeContactNumber() throws SQLException, ClassNotFoundException {
        ArrayList<String>employeeIDs = salaryBo.getAllEmployeeContactNumbers();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(employeeIDs);
        cmbEmployeeContactNumber.setItems(observableList);
    }


    @FXML
    void cmbEmployeeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedCustomerId = cmbEmployeeContactNumber.getSelectionModel().getSelectedItem();
        EmployeeDto employeeDto = salaryBo.findByContactNumber(selectedCustomerId);

        if (employeeDto != null) {

            lblEmName.setText(employeeDto.getEmployeeName());
            lblEmId.setText(employeeDto.getEmployeeId());
        }
    }
}
