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
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.MachineBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.MachineDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.MachineTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class MachinesController implements Initializable {

    @FXML
    private Button buttAdd;

    @FXML
    private ComboBox<String> cmbEmployeeContactNumber;

    @FXML
    private Label lblEmnNic;

    @FXML
    private Label lblEmTask1;

    @FXML
    private Button buttClear;

    @FXML
    private Button buttRemove;

    @FXML
    private Button buttUpdate;

    @FXML
    private Label lblEmId;

    @FXML
    private Label lblEmName;

    @FXML
    private Label lblEmName1;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTask;

    @FXML
    private Label lblid;

    @FXML
    private AnchorPane machineAnchorPane;

    @FXML
    private TableView<MachineTm> tblMachine;

    @FXML
    private TableColumn<MachineTm, String> colmnTask;

    @FXML
    private TableColumn<MachineTm, String> colomnEmId;

    @FXML
    private TableColumn<MachineTm, String> colomnId;

    @FXML
    private TableColumn<MachineTm, String> colomnName;

    @FXML
    private TableColumn<MachineTm, Integer> colomnQu;

    @FXML
    private TextField txtEmId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTask;

    @FXML
    private TextField txtQuantity;


    MachineBo machineBo = (MachineBo) BoFactory.getInstance().getBoType(BoFactory.BoType.MACHINE);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colomnId.setCellValueFactory(new PropertyValueFactory<>("MachineId"));
        colomnName.setCellValueFactory(new PropertyValueFactory<>("MachineName"));
        colmnTask.setCellValueFactory(new PropertyValueFactory<>("machineTask"));
        colomnQu.setCellValueFactory(new PropertyValueFactory<>("machineQuantity"));
        colomnEmId.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(machineAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            refreshPage();
            loadEmployeeContactNumber();
            loadNextEmployeeId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Machine ID");
        }
    }


    @FXML
    void addOnAction(ActionEvent event){
        try {
            String machineId = txtId.getText();
            String machineName = txtName.getText();
            String machineTask = txtTask.getText();
            int machineQuantity = Integer.parseInt(txtQuantity.getText());
            String employeeId = txtEmId.getText();

            MachineDto entity = new MachineDto(machineId,
                    machineName,
                    machineTask,
                    machineQuantity,
                    employeeId
            );

            boolean isSaved = machineBo.save(entity);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Machine").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save the Machine").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    @FXML
    void clearOnAction(ActionEvent event){
        refreshPage();

    }

    @FXML
    void removeOnAction(ActionEvent event){
        try {
            String machineId = txtId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to delete this machine?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted =  machineBo.delete(machineId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION , "Machine deleted").show();

                }else{
                    new Alert(Alert.AlertType.ERROR , "Machine not deleted").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    @FXML
    void tblOnMouseClick(MouseEvent event){
        MachineTm machineTm = tblMachine.getSelectionModel().getSelectedItem();
        if (machineTm != null) {
            txtId.setText(machineTm.getMachineId());
            txtName.setText(machineTm.getMachineName());
            txtTask.setText(machineTm.getMachineTask());
            txtQuantity.setText(String.valueOf(machineTm.getMachineQuantity()));
            txtEmId.setText(machineTm.getEmployeeId());

            buttAdd.setDisable(true);

            buttRemove.setDisable(false);
            buttUpdate.setDisable(false);
            loadTableData();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event){
        try {
            String machineId = txtId.getText();
            String machineName = txtName.getText();
            String machineTask = txtTask.getText();
            int machineQuantity = Integer.parseInt(txtQuantity.getText());
            String employeeId = txtEmId.getText();

            MachineDto entity = new MachineDto(
                    machineId,
                    machineName,
                    machineTask,
                    machineQuantity,
                    employeeId
            );

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to delete this machine?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                boolean isUpdate = machineBo.update(entity);
                if (isUpdate) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully updated the Machine").show();
                    loadTableData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to updated the Machine").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }


    private void refreshPage(){
        try {
            loadNextEmployeeId();
            loadNextMachineId();
            loadTableData();

            buttAdd.setDisable(false);

            buttUpdate.setDisable(true);
            buttRemove.setDisable(true);

            txtName.setText("");
            txtTask.setText("");
            txtQuantity.setText("");
            txtEmId.setText("");
            lblEmName.setText("");
            cmbEmployeeContactNumber.setValue("");
            lblEmId.setText("");
            lblEmnNic.setText("");

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    private void loadTableData(){
        try {
            ArrayList<MachineDto>machineDtos = machineBo.getAll();

            ObservableList<MachineTm> machineTms = FXCollections.observableArrayList();

            for (MachineDto machineDto : machineDtos) {
                MachineTm machineTm = new MachineTm(
                        machineDto.getMachineId(),
                        machineDto.getMachineName(),
                        machineDto.getMachineTask(),
                        machineDto.getMachineQuantity(),
                        machineDto.getEmployeeId()

                );
                machineTms.add(machineTm);
            }
            tblMachine.setItems(machineTms);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    public void loadNextEmployeeId(){
        try {
            String nextEmployeeId = machineBo.getNextId();
            txtEmId.setText(nextEmployeeId);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    public void loadNextMachineId() throws SQLException, ClassNotFoundException {
        try {
            String nextMachineId = machineBo.getNextId();
            txtId.setText(nextMachineId);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    private void loadEmployeeContactNumber(){
        try {
            ArrayList<String>employeeContactNumbers = machineBo.getAllEmployeeContactNumbers();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(employeeContactNumbers);
            cmbEmployeeContactNumber.setItems(observableList);
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();
        }
    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event){
        try {
            String selectedEmployee = cmbEmployeeContactNumber.getSelectionModel().getSelectedItem();
            EmployeeDto employeeDto = machineBo.findByContactNumber(selectedEmployee);

            if (employeeDto != null) {

                lblEmName.setText(employeeDto.getEmployeeName());
                lblEmnNic.setText(employeeDto.getEmployeeNic());
                lblEmId.setText(employeeDto.getEmployeeId());
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Machine Not Found").show();

        }
    }
}
