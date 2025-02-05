package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SupplierDetailDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.SupplierDetail;

import java.sql.SQLException;

public class SupplierDetailsDaoImpl implements SupplierDetailDao {
    @Override
    public boolean saveSupplierDetails(SupplierDetail entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute(
                "insert into supplier_details values (?,?,?)",
                entity.getItemId(),
                entity.getSupplierId(),
                entity.getSupplierRatings()

        );
    }
}
