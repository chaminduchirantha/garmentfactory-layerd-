package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBo extends SuperBo {
    public ArrayList<String> getAllItemIDs() throws SQLException, ClassNotFoundException ;
    public boolean saveProduct(ProductDto productDto) throws SQLException, ClassNotFoundException ;
    public StockDto findById(String selectItemId) throws SQLException, ClassNotFoundException ;
    public String getNextId() throws SQLException, ClassNotFoundException ;

}
