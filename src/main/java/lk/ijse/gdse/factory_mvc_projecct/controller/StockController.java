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
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.StockBo;
import lk.ijse.gdse.factory_mvc_projecct.db.DBConnection;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.StockTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class StockController implements Initializable {

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
    private Label lblDesc;

    @FXML
    private Label lblId;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblQoh;

    @FXML
    private Label lblQu;

    @FXML
    private AnchorPane stockAnchorPane;

    @FXML
    private ComboBox<String> cmbOption;

    @FXML
    private TableView<StockTm> tblStock;

    @FXML
    private TableColumn<StockTm, String> colomnDesc;

    @FXML
    private TableColumn<StockTm, String> colomnId;

    @FXML
    private TableColumn<StockTm, String> colomnPrice;

    @FXML
    private TableColumn<StockTm, String> colomnQoh;

    @FXML
    private TableColumn<StockTm, String> colomnQuality;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQoh;

    @FXML
    private TextField txtQu;

    StockBo stockBo = (StockBo) BoFactory.getInstance().getBoType(BoFactory.BoType.STOCK);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colomnId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colomnDesc.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colomnPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        colomnQoh.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colomnQuality.setCellValueFactory(new PropertyValueFactory<>("itemQuality"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(stockAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();


        try {
            loadNextItemId();
            loadTableData();
            loadCmb();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Item ID");
        }
    }

    private void loadCmb(){
        String[]option = {"GOOD" , "BAD"};
        cmbOption.getItems().addAll(option);
    }

    private void refreshPage() {
        try {
            loadNextItemId();
            loadTableData();

            buttSave.setDisable(false);

            buttUpdate.setDisable(true);
            buttDelete.setDisable(true);

            txtPrice.setText("");
            txtDesc.setText("");
            txtQoh.setText("");
            cmbOption.setValue("");
//            txtQu.setText("");

        } catch (SQLException |ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
        }
    }

    private void loadTableData() {
        try {
            ArrayList<StockDto> stocks = stockBo.getAll();

            ObservableList<StockTm> stockTms = FXCollections.observableArrayList();

            for (StockDto stockDto : stocks) {
                StockTm stockTm = new StockTm(
                        stockDto.getItemCode(),
                        stockDto.getItemPrice(),
                        stockDto.getQuantity(),
                        stockDto.getItemDescription(),
                        stockDto.getItemQuality()
                );
                stockTms.add(stockTm);
            }
            tblStock.setItems(stockTms);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
        }
    }

    public void loadNextItemId() throws SQLException, ClassNotFoundException {
        try {
            String nextItemId = stockBo.getNextId();;
            txtCode.setText(nextItemId);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
        }
    }

    @FXML
    void clearONAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();

    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        try {
            String itemCode = txtCode.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to delete this item?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                boolean isDeleted = stockBo.delete(itemCode);
                if (isDeleted) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION , "item deleted").show();

                }else{
                    new Alert(Alert.AlertType.ERROR , "item not deleted").show();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
        }
    }

    @FXML
    void reportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/ItemRecordReport.jrxml"
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
//           e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @FXML
    void productReportOnAction(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/reports/itemProductReport.jrxml"
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


    @FXML
    void tblOnMouseClick(MouseEvent event) {
        StockTm stockTm = tblStock.getSelectionModel().getSelectedItem();
        if (stockTm != null) {
            txtCode.setText(stockTm.getItemCode());
            txtPrice.setText(String.valueOf(stockTm.getItemPrice()));
            txtQoh.setText(String.valueOf(stockTm.getQuantity()));
            txtDesc.setText(stockTm.getItemDescription());
            cmbOption.setValue(stockTm.getItemQuality());


            buttSave.setDisable(true);

            buttDelete.setDisable(false);
            buttUpdate.setDisable(false);
            loadTableData();
        }
    }


    @FXML
    void saveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            String itemCode = txtCode.getText();
            double itemPrice =Double.parseDouble(txtPrice.getText());
            int qoh = Integer.parseInt(txtQoh.getText());
            String itemDescription = txtDesc.getText();
            String itemQuality = cmbOption.getValue().toString();

            StockDto stockDto = new StockDto(itemCode, itemPrice,qoh, itemDescription, itemQuality);

            boolean isSaved = stockBo.save(stockDto);
            if (isSaved) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Successfully saved the Item").show();
//            loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save the Item").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
//        try {
            String itemCode = txtCode.getText();
            double itemPrice = Double.parseDouble(txtPrice.getText());
            int qoh = Integer.parseInt(txtQoh.getText());
            String itemDescription = txtDesc.getText();
            String itemQuality = cmbOption.getValue().toString();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to Update this item?" , ButtonType.YES , ButtonType.NO);
            Optional<ButtonType> optionalButtonType = alert.showAndWait();

            if (optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
                StockDto stockDto = new StockDto(itemCode,
                        itemPrice,
                        qoh,
                        itemDescription,
                        itemQuality
                );

                boolean isUpdate = stockBo.update(stockDto);
                if (isUpdate) {
                    refreshPage();
                    new Alert(Alert.AlertType.INFORMATION, "Successfully update the Item").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to update the item").show();
                }
            }

//        } catch (SQLException | ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR, "Stock Not Found").show();
//        }
    }
}
