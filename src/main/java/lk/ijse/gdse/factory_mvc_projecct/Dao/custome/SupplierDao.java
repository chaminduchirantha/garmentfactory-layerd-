package lk.ijse.gdse.factory_mvc_projecct.Dao.custome;

import lk.ijse.gdse.factory_mvc_projecct.Dao.CrudDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Supplier;
import lk.ijse.gdse.factory_mvc_projecct.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDao extends CrudDao<Supplier> {
    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException ;
    public SupplierDto findById(String selectedSupId) throws SQLException, ClassNotFoundException ;
}
