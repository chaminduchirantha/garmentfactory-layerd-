package lk.ijse.gdse.factory_mvc_projecct.Dao.custome;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SuperDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.SupplierDetail;

import java.sql.SQLException;

public interface SupplierDetailDao extends SuperDao {
    public boolean saveSupplierDetails(SupplierDetail entity) throws SQLException, ClassNotFoundException ;
}
