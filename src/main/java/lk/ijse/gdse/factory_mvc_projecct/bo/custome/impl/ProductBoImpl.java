package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.ProductDetailDao;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.StockDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.ProductBo;
import lk.ijse.gdse.factory_mvc_projecct.db.DBConnection;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDetailDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBoImpl implements ProductBo {
    StockDao stockDao = (StockDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.STOCK);
    ProductDetailDao productDetailDao = (ProductDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PRODUCTDETAIL);

    @Override
    public ArrayList<String> getAllItemIDs() throws SQLException, ClassNotFoundException {
        return stockDao.getAllItemIDs();
    }

    @Override
    public boolean saveProduct(ProductDto productDto) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            boolean isProductSave = SqlUtil.execute(
                    "insert into product_management values (?,?,?,?)",
                    productDto.getProductId(),
                    productDto.getProductName(),
                    productDto.getProductDate(),
                    productDto.getProductPrice()
            );
            if (isProductSave) {

                ArrayList<ProductDetailDto> productDetailDtos = productDto.getProductDetailDtos();
                ArrayList<ProductDetail> productDetails = new ArrayList<ProductDetail>();

                for (ProductDetailDto dto : productDetailDtos) {
                    ProductDetail productDetail = new ProductDetail();

                    productDetail.setItemId(dto.getItemId());
                    productDetail.setProductId(dto.getProductId());
                    productDetail.setProductDate(dto.getProductDate());
                    productDetail.setItemPrice(dto.getItemPrice());
                    productDetail.setItemQuantity(dto.getItemQuantity());
                    productDetail.setItemName(dto.getItemName());
                    productDetail.setTotalPrice(dto.getTotalPrice());


                    productDetails.add(productDetail);
                }
                boolean isProductDetailListSaved = productDetailDao.saveProductDetailsList(productDetails);

                if (isProductDetailListSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public StockDto findById(String selectItemId) throws SQLException, ClassNotFoundException {
        return stockDao.findById(selectItemId);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("select product_id from product_management order by product_id desc limit 1");
        if (resultSet.next()) {
            String productId = resultSet.getString(1);
            String subString = productId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i + 1;
            return String.format("D%03d", newIndex);
        }
        return "D001";
    }
}

