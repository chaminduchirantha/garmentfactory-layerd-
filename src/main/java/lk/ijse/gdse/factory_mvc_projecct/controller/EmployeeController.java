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
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.EmployeeBo;
import lk.ijse.gdse.factory_mvc_projecct.db.DBConnection;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.EmployeeTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {



    @FXML
    private Button buttClear;

    @FXML
    private Button buttDelete;

    @FXML
    private Button buttReport;

    @FXML
    private Button buttSave;

    @FXML
    private Button buttUpdate;

    @FXML
    private Label emLable;

    @FXML
    private AnchorPane employeeAnchorPane;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNumber;

    @FXML
    private Label lblSection;

    @FXML
    private Label lblTask;

    @FXML
    private ComboBox<String> cmbSection;

    @FXML
    private TableColumn<EmployeeTm, String> idColomn;

    @FXML
    private TableColumn<EmployeeTm, String> nameColomn;

    @FXML
    private TableColumn<EmployeeTm, Integer> ageColomn;

    @FXML
    private TableColumn<EmployeeTm, String> addressColomn;

    @FXML
    private TableColumn<EmployeeTm, String> sectionColmn;

    @FXML
    private TableColumn<EmployeeTm, String> colomnNic;;

    @FXML
    private TableColumn<EmployeeTm, String> phoneColomn;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAdderess;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSection;

    @FXML
    private TextField txtNic;
    private java.awt.Dialog MenuBack;

    EmployeeBo employeeBo = (EmployeeBo) BoFactory.getInstance().getBoType(BoFactory.BoType.EMPLOYEE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColomn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameColomn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        ageColomn.setCellValueFactory(new PropertyValueFactory<>("employeeAge"));
        addressColomn.setCellValueFactory(new PropertyValueFactory<>("employeeAddress"));
        sectionColmn.setCellValueFactory(new PropertyValueFactory<>("employeeSection"));
        colomnNic.setCellValueFactory(new PropertyValueFactory<>("employeeNic"));
        phoneColomn.setCellValueFactory(new PropertyValueFactory<>("employeeContactNumber"));
//
        //employeeAnchorPane.setLeft(employeeAnchorPane);

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(employeeAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadNextEmployeeId();
            refreshPage();
            loadCmb();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Employee ID").show();
        }

    }
    private void refreshPage() {
        try {
            loadNextEmployeeId();
            loadTableData();

            buttSave.setDisable(false);

            buttUpdate.setDisable(true);
            buttDelete.setDisable(true);

            txtName.setText("");
            txtAge.setText("");
            txtAdderess.setText("");
            cmbSection.setValue("");
            txtNic.setText("");
            txtPhone.setText("");

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Employee not found").show();
        }
    }

    @FXML
    void clearONAction(ActionEvent event) {
        refreshPage();

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            String employeeId = txtId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this employee?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = employeeBo.delete(employeeId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Employee deleted").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Employee not deleted").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Employee not found").show();
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/EmployeeRecordReport.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
            System.out.println("employee");
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }


   private void loadTableData(){
       try {
           ArrayList<EmployeeDto> employees = employeeBo.getAll();

           ObservableList<EmployeeTm> employeeTms = FXCollections.observableArrayList();

           for (EmployeeDto employeeDto : employees) {
               EmployeeTm employeeTm = new EmployeeTm(
                       employeeDto.getEmployeeId(),
                       employeeDto.getEmployeeName(),
                       employeeDto.getEmployeeAge(),
                       employeeDto.getEmployeeAddress(),
                       employeeDto.getEmployeeSection(),
                       employeeDto.getEmployeeNic(),
                       employeeDto.getEmployeeContactNumber()
               );
               employeeTms.add(employeeTm);
           }
           tblEmployee.setItems(employeeTms);

       } catch (SQLException | ClassNotFoundException e) {
           new Alert(Alert.AlertType.ERROR, "Employee not found").show();
       }
   }

    public void loadNextEmployeeId(){
       try {
           String nextEmployeeId = employeeBo.getNextId();
           txtId.setText(nextEmployeeId);
       }catch (SQLException | ClassNotFoundException e){
           new Alert(Alert.AlertType.ERROR, "Employee not found").show();
       }
    }


    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            String employeeId = txtId.getText();
            String employeeName = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAdderess.getText();
            String section = cmbSection.getValue();
            String nic = txtNic.getText();
            String phone = txtPhone.getText();

            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #7367F0;");
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
            String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = employeeName.matches(namePattern);
            boolean isValidNic = nic.matches(nicPattern);
            boolean isValidPhone = phone.matches(phonePattern);

            if (!isValidName) {
                System.out.println(txtName.getStyle());
                txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidNic) {
                txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidPhone) {
                txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
            }

            if (isValidName && isValidNic && isValidPhone) {
                EmployeeDto employeeDto = new EmployeeDto(
                    employeeId,
                    employeeName,
                    age,
                    address,
                    section,
                    nic,
                    phone
            );

            boolean isSaved = employeeBo.save(employeeDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Employee").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save the Employee").show();
            }
        }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Employee not found").show();
        }
    }


    @FXML
    void tblEmployeeOnMouseClicked(MouseEvent event){
       try {
           EmployeeTm employeeTm = tblEmployee.getSelectionModel().getSelectedItem();
           if (employeeTm != null) {
               txtId.setText(employeeTm.getEmployeeId());
               txtName.setText(employeeTm.getEmployeeName());
               txtAge.setText(String.valueOf(employeeTm.getEmployeeAge()));
               txtAdderess.setText(employeeTm.getEmployeeAddress());
               cmbSection.setValue(employeeTm.getEmployeeSection());
               txtNic.setText(employeeTm.getEmployeeNic());
               txtPhone.setText(employeeTm.getEmployeeContactNumber());

               buttSave.setDisable(true);

               buttDelete.setDisable(false);
               buttUpdate.setDisable(false);
               loadTableData();
           }

       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, "Employee not found").show();
       }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try {
            String employeeId = txtId.getText();
            String employeeName = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String address = txtAdderess.getText();
            String section = cmbSection.getValue();
            String nic = txtNic.getText();
            String phone = txtPhone.getText();

           txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #7367F0;");
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
            String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = employeeName.matches(namePattern);
            boolean isValidNic = nic.matches(nicPattern);
            boolean isValidPhone = phone.matches(phonePattern);

            if (!isValidName) {
                System.out.println(txtName.getStyle());
                txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidNic) {
                txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
            }

            if (!isValidPhone) {
                txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Update this employee?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                if (isValidName && isValidNic && isValidPhone) {
                    EmployeeDto employeeDto = new EmployeeDto(employeeId,
                            employeeName,
                            age,
                            address,
                            section,
                            nic,
                            phone
                    );

                    boolean isUpdate = employeeBo.update(employeeDto);
                    if (isUpdate) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Employee").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Fail to save the employee").show();
                    }
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Employee not found").show();
        }
    }

    private void loadCmb(){
      String[]section = {"Production" , "Cutting Section" , "Iron Section" , "Checking And Packing" , "Finance Section" , "Quality Controller Section" , "Mechanical Section"};
      cmbSection.getItems().addAll(section);
   }
}
