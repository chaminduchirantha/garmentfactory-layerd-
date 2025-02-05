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
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.bo.BoFactory;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.WorksheetBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.WorkSheetSheduleDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.WorkSheetSheduleTm;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl.EmployeeDaoImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class WorkSheetController implements Initializable {


    @FXML
    private Button buttAdd;

    @FXML
    private Button buttClear;

    @FXML
    private Button buttRemove;

    @FXML
    private Button buttUpdate;

    @FXML
    private TableView<WorkSheetSheduleTm> tblWorksheet;

    @FXML
    private ComboBox<String> cmbEmployeeContactNumber;

    @FXML
    private TableColumn<WorkSheetSheduleTm, String> colomnEmId;

    @FXML
    private TableColumn<WorkSheetSheduleTm, String> colomnEndTime;

    @FXML
    private TableColumn<WorkSheetSheduleTm, String> colomnId;

    @FXML
    private TableColumn<WorkSheetSheduleTm, String> colomnStartTime;

    @FXML
    private TableColumn<WorkSheetSheduleTm, String> colomnTask;

    @FXML
    private Label lblEmName;

    @FXML
    private Label lblEmName1;

    @FXML
    private Label lblEmNic;

    @FXML
    private Label lblEmTask1;

    @FXML
    private Label lblEmId;

    @FXML
    private TextField txtEmId;

    @FXML
    private TextField txtEndTime;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtSatartTime;

    @FXML
    private TextField txtTask;

    @FXML
    private AnchorPane workShhetAnchorPane;

    WorksheetBo worksheetBo = (WorksheetBo) BoFactory.getInstance().getBoType(BoFactory.BoType.WORKSHEET);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colomnId.setCellValueFactory(new PropertyValueFactory<>("workSheetId"));
        colomnStartTime.setCellValueFactory(new PropertyValueFactory<>("workStartTime"));
        colomnEndTime.setCellValueFactory(new PropertyValueFactory<>("workEndTime"));
        colomnTask.setCellValueFactory(new PropertyValueFactory<>("taskName"));
        colomnEmId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(workShhetAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadNextWorksheetId();
            loadTableData();
            loadEmployeeContactNumber();
            loadNextEmployeeId();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Worksheet ID");
        }
    }

    @FXML
    void addOnAction(ActionEvent event){
        try {
            String workSheetId = txtId.getText();
            String workStartTime = txtSatartTime.getText();
            String workEndTime = txtEndTime.getText();
            String workTaskName = txtTask.getText();
            String employeeId = txtEmId.getText();


            WorkSheetSheduleDto workSheetSheduleDto = new WorkSheetSheduleDto(
                    workSheetId,
                    workStartTime,
                    workEndTime,
                    workTaskName,
                    employeeId
            );

            boolean isSaved = worksheetBo.save(workSheetSheduleDto);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Schedule").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save the Schedule").show();
            }

        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Worksheet not found").show();

        }
    }

    @FXML
    void tblMouseOnAction(MouseEvent event){
        WorkSheetSheduleTm workSheetSheduleTm = tblWorksheet.getSelectionModel().getSelectedItem();
        if (workSheetSheduleTm != null) {
            txtId.setText(workSheetSheduleTm.getWorkSheetId());
            txtSatartTime.setText(workSheetSheduleTm.getWorkStartTime());
            txtEndTime.setText(workSheetSheduleTm.getWorkEndTime());
            txtTask.setText(workSheetSheduleTm.getTaskName());
            txtEmId.setText(workSheetSheduleTm.getEmployeeId());

            buttAdd.setDisable(true);

            buttRemove.setDisable(false);
            buttUpdate.setDisable(false);
            loadTableData();
        }
    }

    @FXML
    void clearOnAction(ActionEvent event){
        refreshPage();
    }

    @FXML
    void removeOnAction(ActionEvent event){
        try {
            String workSheetId = txtId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to delete this workSheetShedule?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = worksheetBo.delete(workSheetId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION , "Employee deleted").show();

                }else{
                    new Alert(Alert.AlertType.ERROR , "Employee not deleted").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Worksheet not found").show();

        }
    }

    @FXML
    void updateOnAction(ActionEvent event){
        try {
            String workSheetId = txtId.getText();
            String workStartTime = txtSatartTime.getText();
            String workEndTime = txtEndTime.getText();
            String workTaskName = txtTask.getText();
            String employeeId = txtEmId.getText();


            WorkSheetSheduleDto workSheetSheduleDto = new WorkSheetSheduleDto(
                    workSheetId,
                    workStartTime,
                    workEndTime,
                    workTaskName,
                    employeeId
            );

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to Update this workSheetShedule?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isUpdate = worksheetBo.update(workSheetSheduleDto);
                if (isUpdate) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully updated the Schedule").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to updated the Schedule").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Worksheet not found").show();
        }
    }

    public void loadNextWorksheetId() throws SQLException, ClassNotFoundException {
        try {
            String nextWorkSheetId = worksheetBo.getNextId();
            txtId.setText(nextWorkSheetId);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Worksheet not found").show();
        }
    }
    public void loadNextEmployeeId() throws SQLException, ClassNotFoundException {
        try {
            EmployeeDao employeeDao = new EmployeeDaoImpl();
            String nextWorkSheetId = employeeDao.getNextId();
            txtEmId.setText(nextWorkSheetId);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Worksheet not found").show();
        }
    }

    private void loadTableData(){
        try {
            ArrayList<WorkSheetSheduleDto>workSheets = worksheetBo.getAll();

            ObservableList<WorkSheetSheduleTm> workSheetSheduleTms = FXCollections.observableArrayList();

            for (WorkSheetSheduleDto workSheetSheduleDto : workSheets) {
                WorkSheetSheduleTm workSheetSheduleTm = new WorkSheetSheduleTm(
                        workSheetSheduleDto.getWorkSheetId(),
                        workSheetSheduleDto.getWorkStartTime(),
                        workSheetSheduleDto.getWorkEndTime(),
                        workSheetSheduleDto.getTaskName(),
                        workSheetSheduleDto.getEmployeeId()
                );
                workSheetSheduleTms.add(workSheetSheduleTm);
            }
            tblWorksheet.setItems(workSheetSheduleTms);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR , "Worksheet Not Found").show();
        }
    }

    private void refreshPage() {
        try {
            loadNextWorksheetId();
            loadTableData();

            buttAdd.setDisable(false);

            buttUpdate.setDisable(true);
            buttRemove.setDisable(true);

            txtSatartTime.setText("");
            txtEndTime.setText("");
            txtTask.setText("");
            lblEmName.setText("");
            cmbEmployeeContactNumber.setValue("");
            lblEmNic.setText("");
            lblEmId.setText("");

        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR , "Worksheet not found").show();
        }
    }

    public void tblOnMouseClick(MouseEvent mouseEvent) {
    }

    private void loadEmployeeContactNumber() throws SQLException, ClassNotFoundException {
        try {
            ArrayList<String>employeeIDs = worksheetBo.getAllEmployeeContactNumbers();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(employeeIDs);
            cmbEmployeeContactNumber.setItems(observableList);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR , "Employee not found").show();
        }
    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        try {
            String selectedCustomerId = cmbEmployeeContactNumber.getSelectionModel().getSelectedItem();
            EmployeeDto employeeDto = worksheetBo.findByContactNumber(selectedCustomerId);

            if (employeeDto != null) {

                lblEmName.setText(employeeDto.getEmployeeName());
                lblEmNic.setText(employeeDto.getEmployeeNic());
                lblEmId.setText(employeeDto.getEmployeeId());
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR , "Employee not found").show();
        }
    }
}
