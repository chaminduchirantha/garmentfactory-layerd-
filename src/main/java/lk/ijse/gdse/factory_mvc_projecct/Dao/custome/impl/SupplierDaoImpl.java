package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SupplierDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDaoImpl implements SupplierDao {
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("select supplier_id from supplier_management order by supplier_id desc limit 1");
        if (resultSet.next()) {
            String supplierId = resultSet.getString(1);
            String subString = supplierId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i + 1;
            return String.format("S%03d", newIndex);
        }
        return "S001";
    }
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into supplier_management values(?,?,?,?,?,?)", entity.getSupplierId(), entity.getSupplierName(), entity.getSupplierAddress(), entity.getSupplierAge(), entity.getSupplierNICNumber(), entity.getSupplierContactNumber());
    }

    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst =  SqlUtil.execute("select * from supplier_management");
        ArrayList<Supplier> suppliers = new ArrayList<>();

        while (rst.next()){
            Supplier entity = new Supplier();
            entity.setSupplierId(rst.getString("supplier_id"));
            entity.setSupplierName(rst.getString("supplier_name"));
            entity.setSupplierAddress(rst.getString("supplier_address"));
            entity.setSupplierAge(rst.getInt("supplier_age"));
            entity.setSupplierContactNumber(rst.getString("supplier_contact_number"));
            entity.setSupplierNICNumber(rst.getString("supplier_Nic_no"));

            suppliers.add(entity);
        }
        return suppliers;
    }

    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update supplier_management set supplier_name=?, supplier_address=?, supplier_age=?, supplier_contact_number=?, supplier_Nic_no=? where supplier_id=?", entity.getSupplierName(), entity.getSupplierAddress(), entity.getSupplierAge(), entity.getSupplierNICNumber(), entity.getSupplierContactNumber(), entity.getSupplierId());
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from supplier_management where supplier_id=?", supplierId);
    }

    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("select supplier_id from supplier_management");
        ArrayList<String> supplierIds = new ArrayList<>();
        while (rst.next()) {
            supplierIds.add(rst.getString(1));
        }
        return supplierIds;
    }

    public Supplier findById(String selectedSupId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("select * from supplier_management where supplier_id=?", selectedSupId);

        if (rst.next()) {
            return new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6));

        }
        return null;
    }
}
