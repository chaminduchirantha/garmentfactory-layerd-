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
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.AttendanceBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.AttendenceDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.AttendenceTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AttendenceController implements Initializable {




    @FXML
    private AnchorPane attendenceAnchorPane;

    @FXML
    private Button buttAdd;

    @FXML
    private Button buttClear;

    @FXML
    private ComboBox<String> cmbEmployeeContactNumber;

    @FXML
    private ComboBox<String> cmbShiftType;

    @FXML
    private Button buttRemove;

    @FXML
    private Button buttUpdate;

    @FXML
    private Label lblEmName;

    @FXML
    private Label lblEmName1;

    @FXML
    private Label lblEId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEXtime;

    @FXML
    private Label lblEntime;

    @FXML
    private Label lblId;

    @FXML
    private TableView<AttendenceTm> tblAttendence;

    @FXML
    private TableColumn<AttendenceTm, String> colomnAttendenceDate;

    @FXML
    private TableColumn<AttendenceTm, String> colomnEmployeId;

    @FXML
    private TableColumn<AttendenceTm, String> colomnEntryTmie;

    @FXML
    private TableColumn<AttendenceTm, String> colomnExiteTime;

    @FXML
    private TableColumn<?, ?> colomnShiftType;

    @FXML
    private TableColumn<AttendenceTm, String> colomnId;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField txtEnployeeId;

    @FXML
    private TextField txtEntryTime;

    @FXML
    private TextField txtExiteTime;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtEmId;

    AttendanceBo attendanceBo = (AttendanceBo) BoFactory.getInstance().getBoType(BoFactory.BoType.ATTENDANCE);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colomnId.setCellValueFactory(new PropertyValueFactory<>("attendenceId"));
        colomnAttendenceDate.setCellValueFactory(new PropertyValueFactory<>("attendenceDate"));
        colomnEntryTmie.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        colomnExiteTime.setCellValueFactory(new PropertyValueFactory<>("exitTime"));
        colomnShiftType.setCellValueFactory(new PropertyValueFactory<>("shiftType"));
        colomnEmployeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(attendenceAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadNextAttendanceId();
            refreshPage();
            loadEmployeeContactNumber();
            loadNextEmployeeId();
            loadCmb();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Attendance ID");

        }
    }

    private void loadNextAttendanceId(){
        try {
            String nextAttendanceId = attendanceBo.getNextId();
            txtId.setText(nextAttendanceId);
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }

    private void loadNextEmployeeId(){
        try {
            String nextAttendanceId = attendanceBo.getNextId();
            txtEmId.setText(nextAttendanceId);
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }

    private void loadCmb(){
        String [] shiftTypes = {"Day" , "Night" , "Flexible"};
        cmbShiftType.getItems().addAll(shiftTypes);
    }

    @FXML
    void addOnAction(ActionEvent event){
        try {
            String AttendenceId = txtId.getText();
            String entryTime = txtEntryTime.getText();
            String exitTime = txtExiteTime.getText();
            LocalDate attendenceDate = datePicker.getValue();
            String shiftType = cmbShiftType.getValue();
            String employeeId = txtEmId.getText();


            AttendenceDto attendenceDto = new AttendenceDto(AttendenceId, entryTime, exitTime, attendenceDate, shiftType, employeeId);

            boolean isSaved = attendanceBo.save(attendenceDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Attendece").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save the Attendence").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }


    private void loadTableData(){
        try {
            ArrayList<AttendenceDto> attendances = attendanceBo.getAll();

            ObservableList<AttendenceTm> attendenceTms = FXCollections.observableArrayList();

            for (AttendenceDto attendenceDto : attendances) {
                AttendenceTm attendenceTm = new AttendenceTm(
                        attendenceDto.getAttendenceId(),
                        attendenceDto.getEntryTime(),
                        attendenceDto.getExitTime(),
                        attendenceDto.getAttendenceDate(),
                        attendenceDto.getShiftType(),
                        attendenceDto.getEmployeeId()

                );
                attendenceTms.add(attendenceTm);
            }
            tblAttendence.setItems(attendenceTms);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }


    private void refreshPage(){
        try {
            loadNextAttendanceId();
            loadTableData();

            buttAdd.setDisable(false);

            buttUpdate.setDisable(true);
            buttRemove.setDisable(true);

            txtEntryTime.setText("");
            txtExiteTime.setText("");
            datePicker.setTooltip(new Tooltip("Date"));
            cmbShiftType.setValue("");
            lblEmName.setText("");
            lblEId.setText("");
            cmbEmployeeContactNumber.setValue("");

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }

    @FXML
    void clearOnActon(ActionEvent event)  {
        try {
            refreshPage();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }

    @FXML
    void removeOnAction(ActionEvent event){
        try {
            String attendanceId = txtId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Attendance?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = attendanceBo.delete(attendanceId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Attendance deleted").show();
                    loadTableData();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Attendance not deleted").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            String AttendenceId = txtId.getText();
            String entryTime = txtEntryTime.getText();
            String exitTime = txtExiteTime.getText();
            LocalDate attendenceDate = datePicker.getValue();
            String shiftType = cmbShiftType.getValue();
            String employeeId = txtEmId.getText();

            AttendenceDto attendenceDto = new AttendenceDto(AttendenceId,
                    entryTime,
                    exitTime,
                    attendenceDate,
                    shiftType,
                    employeeId
            );

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Update this Attendance?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                boolean isUpdate = attendanceBo.update(attendenceDto);
                if (isUpdate) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Update the Attendance").show();
                    loadTableData();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to update the Attendance").show();
                }
            }

        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
            System.out.println(e.getMessage());
        }
    }

    public void tblMouseClick(MouseEvent mouseEvent){

    }

    public void tblOnMouseClick(MouseEvent mouseEvent) {
        try {
            AttendenceTm attendenceTm = tblAttendence.getSelectionModel().getSelectedItem();
            if (attendenceTm != null) {
                txtId.setText(attendenceTm.getAttendenceId());
                txtEntryTime.setText(attendenceTm.getEntryTime());
                txtExiteTime.setText(attendenceTm.getExitTime());
                datePicker.setValue(attendenceTm.getAttendenceDate());
                cmbShiftType.setValue(attendenceTm.getShiftType());
                txtEmId.setText(attendenceTm.getEmployeeId());

                buttAdd.setDisable(true);
                buttRemove.setDisable(false);
                buttUpdate.setDisable(false);
                loadTableData();
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Attendence Not Found").show();
        }
    }

    private void loadEmployeeContactNumber(){
        try {
            ArrayList<String> employeeIDs = attendanceBo.getAllEmployeeContactNumbers();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(employeeIDs);
            cmbEmployeeContactNumber.setItems(observableList);
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Employee Not Found").show();
        }
    }

    @FXML
    void cmbEmployeeOnAction(ActionEvent event) {
        try {
            String selectedCustomerId = cmbEmployeeContactNumber.getSelectionModel().getSelectedItem();
            EmployeeDto employeeDto = attendanceBo.findByContactNumber(selectedCustomerId);

            if (employeeDto != null) {

                lblEmName.setText(employeeDto.getEmployeeName());
                lblEId.setText(employeeDto.getEmployeeId());

            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Employee Not Found").show();
        }
    }
}
