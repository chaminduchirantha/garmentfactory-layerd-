package lk.ijse.gdse.factory_mvc_projecct.Dao.custome;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SuperDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDetailDao extends SuperDao {
     boolean saveProductDetailsList(ArrayList<ProductDetail>productDetails) throws SQLException, ClassNotFoundException ;
     boolean saveProductDetail(ProductDetail entity) throws SQLException, ClassNotFoundException ;
}
