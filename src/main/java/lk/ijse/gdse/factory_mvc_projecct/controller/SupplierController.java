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
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.SupplierBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.SupplierDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.SupplierTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {



    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TableColumn<SupplierTm, String> PhoneColomn;

    @FXML
    private TableColumn<SupplierTm, String> addressColomn;

    @FXML
    private TableColumn<SupplierTm, Integer> ageColomn;

    @FXML
    private TableColumn<SupplierTm, String> idColomn;

    @FXML
    private TableColumn<SupplierTm, String> nameColomn;

    @FXML
    private TableColumn<SupplierTm, String> nicColomn;

    @FXML
    private Button buttClear;

    @FXML
    private Button buttDelete;

    @FXML
    private Button buttSave;

    @FXML
    private Button buttUpdate;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblAge;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNicNumber;

    @FXML
    private Label lblNumber;

    @FXML
    private AnchorPane supplierAnchorPane;

    @FXML
    private TextField txtAdderess;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPhone;

    SupplierBo supplierBo = (SupplierBo) BoFactory.getInstance().getBoType(BoFactory.BoType.SUPPLIER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColomn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        nameColomn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        addressColomn.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        ageColomn.setCellValueFactory(new PropertyValueFactory<>("supplierAge"));
        PhoneColomn.setCellValueFactory(new PropertyValueFactory<>("supplierContactNumber"));
        nicColomn.setCellValueFactory(new PropertyValueFactory<>("supplierNICNumber"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(supplierAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        try {
            loadTableData();
            refreshPage();
            loadNextSupplierId();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Supplier ID");

        }
    }

    public void loadNextSupplierId() throws SQLException, ClassNotFoundException {

        String nextSupplierId = supplierBo.getNextId();
        txtId.setText(nextSupplierId);
    }

    private void loadTableData(){
        try {
            ArrayList<SupplierDto> suppliers = supplierBo.getAll();

            ObservableList<SupplierTm> supplierTms = FXCollections.observableArrayList();

            for (SupplierDto supplierDto : suppliers) {
                SupplierTm supplierTm = new SupplierTm(
                        supplierDto.getSupplierId(),
                        supplierDto.getSupplierName(),
                        supplierDto.getSupplierAddress(),
                        supplierDto.getSupplierAge(),
                        supplierDto.getSupplierContactNumber(),
                        supplierDto.getSupplierNICNumber()

                        );
                supplierTms.add(supplierTm);
            }
            tblSupplier.setItems(supplierTms);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "supplier not Found").show();
        }
    }

    private void refreshPage(){
        try {
            loadTableData();
            loadNextSupplierId();

            buttSave.setDisable(false);

            buttUpdate.setDisable(true);
            buttDelete.setDisable(true);

            txtName.setText("");
            txtAge.setText("");
            txtAdderess.setText("");
            txtPhone.setText("");
            txtNic.setText("");

        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }

    @FXML
    void clearONAction(ActionEvent event){
        try {
            refreshPage();
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event){
        try {
            String supplierId = txtId.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this Supplier?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = supplierBo.delete(supplierId);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "supplier deleted").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "supplier not deleted").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }

    @FXML
    void saveOnAction(ActionEvent event){
        try {
            String supplierId = txtId.getText();
            String supplierName = txtName.getText();
            String supplierAddress = txtAdderess.getText();
            int supplierAge = Integer.parseInt(txtAge.getText());
            String supplierContactNumber = txtPhone.getText();
            String supplierNic =txtNic.getText();

            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #7367F0;");
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
            String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = supplierName.matches(namePattern);
            boolean isValidNic = supplierNic.matches(nicPattern);
            boolean isValidPhone = supplierContactNumber.matches(phonePattern);

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
                SupplierDto supplierDto = new SupplierDto(supplierId,
                        supplierName,
                        supplierAddress,
                        supplierAge,
                        supplierContactNumber,
                        supplierNic
                );

                boolean isSaved = supplierBo.save(supplierDto);
                if (isSaved) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Supplier").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to save the Supplier").show();
                }
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }

    @FXML
    void tblSupplierOnMouseClicked(MouseEvent event) {
        try {
            SupplierTm supplierTm = tblSupplier.getSelectionModel().getSelectedItem();
            if (supplierTm != null) {
                txtId.setText(supplierTm.getSupplierId());
                txtName.setText(supplierTm.getSupplierName());
                txtAge.setText(String.valueOf(supplierTm.getSupplierAge()));
                txtAdderess.setText(supplierTm.getSupplierAddress());
                txtPhone.setText(supplierTm.getSupplierContactNumber());
                txtNic.setText(supplierTm.getSupplierNICNumber());

                buttSave.setDisable(true);

                buttDelete.setDisable(false);
                buttUpdate.setDisable(false);
                loadTableData();
            }
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event){
        try {
            String supplierId = txtId.getText();
            String supplierName = txtName.getText();
            String supplierAddress = txtAdderess.getText();
            int supplierAge = Integer.parseInt(txtAge.getText());
            String supplierContactNumber = txtPhone.getText();
            String supplierNic = txtNic.getText();

            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #7367F0;");
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #7367F0;");

            String namePattern = "^[A-Za-z ]+$";
            String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
            String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

            boolean isValidName = supplierName.matches(namePattern);
            boolean isValidNic = supplierNic.matches(nicPattern);
            boolean isValidPhone = supplierContactNumber.matches(phonePattern);

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
                SupplierDto supplierDto = new SupplierDto(supplierId,
                        supplierName,
                        supplierAddress,
                        supplierAge,
                        supplierContactNumber,
                        supplierNic


                );

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this Supplier?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> optionalButtonType = alert.showAndWait();

                if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {

                    boolean isUpdate = supplierBo.update(supplierDto);
                    if (isUpdate) {
                        refreshPage();
                        new Alert(Alert.AlertType.INFORMATION, "Successfully update the Supplier").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Fail to update the Supplier").show();
                    }
                }
            }
        } catch (SQLException |ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Found").show();
        }
    }
}
