package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException ;
    public boolean save(StockDto stockDto) throws SQLException, ClassNotFoundException ;
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean update(StockDto stockDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String itemCode) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllItemIDs() throws SQLException, ClassNotFoundException ;
    public StockDto findById(String selectItemId) throws SQLException, ClassNotFoundException ;
    public boolean reduceQty(ProductDetail entity) throws SQLException, ClassNotFoundException ;
}
