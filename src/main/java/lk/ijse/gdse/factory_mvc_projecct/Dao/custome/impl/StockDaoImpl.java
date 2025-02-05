package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.StockDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Stock;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.dto.StockDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDaoImpl implements StockDao {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "select item_id from item_management order by item_id desc limit 1");
        if (resultSet.next()) {
            String itemCode = resultSet.getString(1);
            String subString = itemCode.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("P%03d", newIndex);
        }
        return "P001";
    }


    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into item_management values(?,?,?,?,?)" , entity.getItemCode(),entity.getItemPrice(),entity.getQuantity(),entity.getItemDescription(),entity.getItemQuality());
    }


    @Override
    public ArrayList<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from item_management");
        ArrayList<Stock> stocks = new ArrayList<>();

        while (rst.next()){
            Stock entity = new Stock();
            entity.setItemCode(rst.getString("item_id"));
            entity.setItemPrice(rst.getDouble("item_price"));
            entity.setQuantity(rst.getInt("item_quantity_on_hand"));
            entity.setItemDescription(rst.getString("item_description"));
            entity.setItemQuality(rst.getString("item_quality"));

            stocks.add(entity);
        }
        return stocks;
    }


    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update item_management set item_price=?, item_quantity_on_hand=?, item_description=?,item_quality=? where item_id=?" , entity.getItemPrice(),entity.getQuantity(),entity.getItemDescription(), entity.getItemQuality(), entity.getItemCode());
    }


    @Override
    public boolean delete(String itemCode) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from item_management where item_id=?", itemCode);
    }


    @Override
    public ArrayList<String> getAllItemIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("select item_id from item_management");
        ArrayList<String> itemIds = new ArrayList<>();
        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }
        return itemIds;
    }


    @Override
    public StockDto findById(String selectItemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("select * from item_management where item_id=?", selectItemId);

        if (rst.next()) {
            return new StockDto(rst.getString(1), rst.getDouble(2), rst.getInt(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean reduceQty(ProductDetail entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute(
                "update item_management set item_quantity_on_hand = item_quantity_on_hand - ? where item_id = ?",
                entity.getItemQuantity(),
                entity.getItemId()
        );
    }
}
