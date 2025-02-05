package lk.ijse.gdse.factory_mvc_projecct.Dao.custome;

import lk.ijse.gdse.factory_mvc_projecct.Dao.CrudDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Stock;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockDao extends CrudDao<Stock> {
    public ArrayList<String> getAllItemIDs() throws SQLException, ClassNotFoundException ;
    public StockDto findById(String selectItemId) throws SQLException, ClassNotFoundException ;
    public boolean reduceQty(ProductDetail productDetailDto) throws SQLException, ClassNotFoundException ;
}
