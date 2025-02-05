package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.ProductDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Product;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;

import java.sql.*;
import java.util.ArrayList;

public class ProductDaoImpl implements ProductDao {
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

    @Override
    public boolean save(Product entity) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Product entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return false;
    }
}


