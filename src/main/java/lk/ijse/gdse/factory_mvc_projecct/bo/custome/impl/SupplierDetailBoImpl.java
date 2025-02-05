package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SupplierDetailDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.SupplierDetail;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.SupplierDetailBo;

import java.sql.SQLException;

public class SupplierDetailBoImpl implements SupplierDetailBo {
    SupplierDetailDao supplierDetailDao = (SupplierDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIERDETAIL);

    public boolean saveSupplierDetails(SupplierDetail supplierDetail) throws SQLException, ClassNotFoundException {
        return supplierDetailDao.saveSupplierDetails(supplierDetail);
    }
}
