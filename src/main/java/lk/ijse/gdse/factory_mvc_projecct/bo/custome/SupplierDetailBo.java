package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.Entity.SupplierDetail;

import java.sql.SQLException;

public interface SupplierDetailBo {
    public boolean saveSupplierDetails(SupplierDetail entity) throws SQLException, ClassNotFoundException ;
}
