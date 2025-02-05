package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDetailDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDetailBo extends SuperBo {
    public boolean saveProductDetailsList(ArrayList<ProductDetailDto> productDetails) throws SQLException, ClassNotFoundException ;
    public boolean saveProductDetail(ProductDetailDto productDetailDto) throws SQLException, ClassNotFoundException ;
}
