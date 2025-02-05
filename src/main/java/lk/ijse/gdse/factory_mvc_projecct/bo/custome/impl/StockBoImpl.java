package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.StockDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Stock;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.StockBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockBoImpl implements StockBo {

    StockDao stockDao = (StockDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.STOCK);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return stockDao.getNextId();
    }

    @Override
    public boolean save(StockDto stockDto) throws SQLException, ClassNotFoundException {
        return stockDao.save(new Stock(stockDto.getItemCode(),stockDto.getItemPrice(),stockDto.getQuantity(),stockDto.getItemDescription(),stockDto.getItemQuality()));
    }

    @Override
    public ArrayList<StockDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StockDto> stockDtos = new ArrayList<>();
        ArrayList<Stock> stocks = stockDao.getAll();
        for (Stock stock : stocks) {
            stockDtos.add(new StockDto(stock.getItemCode(),stock.getItemPrice(),stock.getQuantity(),stock.getItemDescription(),stock.getItemQuality()));
        }
        return stockDtos;
    }

    @Override
    public boolean update(StockDto stockDto) throws SQLException, ClassNotFoundException {
        return stockDao.update(new Stock(stockDto.getItemCode(),stockDto.getItemPrice(),stockDto.getQuantity(),stockDto.getItemDescription(),stockDto.getItemQuality()));
    }

    @Override
    public boolean delete(String itemCode) throws SQLException, ClassNotFoundException {
        return stockDao.delete(itemCode);
    }

    @Override
    public ArrayList<String> getAllItemIDs() throws SQLException, ClassNotFoundException {
        return stockDao.getAllItemIDs();
    }

    @Override
    public StockDto findById(String selectItemId) throws SQLException, ClassNotFoundException {
       return stockDao.findById(selectItemId);
    }

    @Override
    public boolean reduceQty(ProductDetail entity) throws SQLException, ClassNotFoundException {
        return stockDao.reduceQty(entity);
    }
}
