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
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.ProductBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDetailDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.tm.CartTm;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private TableColumn<CartTm, Double> PriceColomn;

    @FXML
    private Label ProductPrice;

    @FXML
    private Button btnAddCart;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnProduct;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private ComboBox<String> cmbProductCatogry;

    @FXML
    private Label emLable;

    @FXML
    private TableColumn<CartTm, String> idColomn;

    @FXML
    private TableColumn<CartTm, String> itemNameColmn;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblId;

    @FXML
    private Label lblId1;

    @FXML
    private Label lblId11;

    @FXML
    private Label lblId111;

    @FXML
    private Label lblId2;

    @FXML
    private Label lblItemCode;

    @FXML
    private Label lblQoh;

    @FXML
    private Label lblQoh1;


    @FXML
    private Label lblQuality;

    @FXML
    private Label lblPrice;

    @FXML
    private AnchorPane productAnchorPane;

    @FXML
    private Label productQuantity;

    @FXML
    private TableColumn<CartTm, String> quantityColmn;

    @FXML
    private TableView<CartTm> tblProduct;

    @FXML
    private TableColumn<CartTm, String> totalColmn;

    @FXML
    private DatePicker txtDatePicker;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQuantuty;

    @FXML
    private TextField txtProductName;

    ProductBo productBo = (ProductBo) BoFactory.getInstance().getBoType(BoFactory.BoType.PRODUCT);
    private final ObservableList<CartTm> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColomn.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        itemNameColmn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantityColmn.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        PriceColomn.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        totalColmn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        TranslateTransition slider = new TranslateTransition();
        slider.setNode(productAnchorPane);
        slider.setDuration(Duration.seconds(1.0));
        slider.setFromX(-200);
        slider.setToX(0);
        slider.play();

        tblProduct.setItems(cartTMS);

        try {
            refreshPage();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        txtId.setText(productBo.getNextId());

        txtDatePicker.setTooltip(new Tooltip("Date"));

        loadItemIds();
        loadCmb();

        cmbItemId.getSelectionModel().clearSelection();
        lblDesc.setText("");
        lblQoh1.setText("");
        lblQuality.setText("");

        cartTMS.clear();

        tblProduct.refresh();
    }

    private void loadItemIds() {
        try {
            ArrayList<String> itemIDs = productBo.getAllItemIDs();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(itemIDs);
            cmbItemId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Stock not found").show();
        }
    }

    private void loadCmb(){
        String[]option = {"Mens" , "Women's"};
        cmbProductCatogry.getItems().addAll(option);
    }


    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String selectedItemId = cmbItemId.getValue();

        if (selectedItemId == null) {
            new Alert(Alert.AlertType.ERROR, "Please select item..!").show();
            return;
        }

        String cartQtyString = txtQuantuty.getText();

        String qtyPattern = "^[0-9]+$";


        if (!cartQtyString.matches(qtyPattern)){
            new Alert(Alert.AlertType.ERROR, "Please enter valid quantity..!").show();
            return;
        }

        String itemName = lblDesc.getText();
        int cartQty = Integer.parseInt(cartQtyString);
        int qtyOnHand = Integer.parseInt(lblQoh1.getText());

        if (qtyOnHand < cartQty) {
            new Alert(Alert.AlertType.ERROR, "Not enough items..!").show();
            return;
        }


        double unitPrice = Double.parseDouble(lblPrice.getText());
        double total = unitPrice * cartQty;
        double itemPrice = Double.parseDouble(lblPrice.getText());
        int itemQuantity = Integer.parseInt(txtQuantuty.getText());

        txtQuantuty.setText("");

        for (CartTm cartTM : cartTMS) {

            if (cartTM.getItemId().equals(selectedItemId)) {
                int newQty = cartTM.getItemQuantity() + cartQty;
                cartTM.setItemQuantity(newQty);
                cartTM.setTotalPrice(unitPrice * newQty);

                tblProduct.refresh();
                return;
            }
        }



        Button btn = new Button("Remove");

        CartTm newCartTM = new CartTm(
                selectedItemId,
                itemPrice,
                itemQuantity,
                itemName,
                total
        );


        btn.setOnAction(actionEvent -> {

            cartTMS.remove(newCartTM);

            tblProduct.refresh();
        });

        cartTMS.add(newCartTM);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

            if (tblProduct.getItems().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please add items to cart..!").show();
                return;
            }

            String productId = txtId.getText();
            LocalDate dateOfProduct = txtDatePicker.getValue();
            String productName = txtProductName.getText();
            String productCategory = cmbProductCatogry.getValue();
            double productPrice = 0;
            ArrayList<ProductDetailDto> productDetails = new ArrayList<>();


            for (CartTm cartTm : cartTMS) {

                ProductDetailDto productDetailDto = new ProductDetailDto(
                        cartTm.getItemId(),
                        productId,
                        dateOfProduct,
                        cartTm.getItemPrice(),
                        cartTm.getItemQuantity(),
                        cartTm.getItemName(),
                        cartTm.getTotalPrice()
                );

                productDetails.add(productDetailDto);
            }

            for (ProductDetailDto productDetailDto : productDetails) {
                productPrice += productDetailDto.getTotalPrice();

            }

            System.out.println(productPrice);
            System.out.println(productDetails);

            ProductDto productDto = new ProductDto(
                    productId,
                    productName,
                    productPrice,
                    dateOfProduct,
                    productCategory,
                    productDetails

            );

            boolean isSaved = productBo.saveProduct(productDto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Product saved..!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Saved fail..!").show();
            }
        }



    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();

    }

    @FXML
    void cmbStockOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        StockDto stockDto = productBo.findById(selectedItemId);

        if (stockDto != null) {

            lblPrice.setText(String.valueOf(stockDto.getItemPrice()));
            lblQoh1.setText(String.valueOf(stockDto.getQuantity()));
            lblDesc.setText(String.valueOf(stockDto.getItemDescription()));
            lblQuality.setText(stockDto.getItemQuality());
        }
    }


    @FXML
    void tblProductOnMouseClicked(MouseEvent event) {

    }


    public void cmbSupplierOnAction(ActionEvent actionEvent) {
    }
}
