package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.ProductDetailDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.bo.BoFactory;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.StockBo;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDetailDaoImpl implements ProductDetailDao {
    private final StockBo stockBo = (StockBo) BoFactory.getInstance().getBoType(BoFactory.BoType.STOCK);

    @Override
    public boolean saveProductDetailsList(ArrayList<ProductDetail> productDetails) throws SQLException, ClassNotFoundException {
        for (ProductDetail entity : productDetails) {
            boolean isProductDetailsSaved = saveProductDetail(entity);
            if (!isProductDetailsSaved) {
                return false;
            }

            boolean isItemUpdated = stockBo.reduceQty(entity);
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean saveProductDetail(ProductDetail entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute(
                "insert into product_details values (?,?,?,?,?,?)",
                entity.getItemId(),
                entity.getProductId(),
                entity.getItemName(),
                entity.getItemPrice(),
                entity.getTotalPrice(),
                entity.getItemQuantity()
        );
    }
}
