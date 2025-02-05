package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SupplierDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Supplier;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.SupplierBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBoImpl implements SupplierBo {
    SupplierDao supplierDao = (SupplierDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIER);

    public String getNextId() throws SQLException, ClassNotFoundException {
        return supplierDao.getNextId();
    }

    public boolean save(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDao.save((new Supplier(supplierDto.getSupplierId(),supplierDto.getSupplierName(),supplierDto.getSupplierAddress(),supplierDto.getSupplierAge(),supplierDto.getSupplierContactNumber(),supplierDto.getSupplierNICNumber())));
    }

    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier>suppliers = supplierDao.getAll();
        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getSupplierAge(),supplier.getSupplierContactNumber(),supplier.getSupplierNICNumber()));
        }
        return supplierDtos;
    }

    public boolean update(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDao.update((new Supplier(supplierDto.getSupplierId(),supplierDto.getSupplierName(),supplierDto.getSupplierAddress(),supplierDto.getSupplierAge(),supplierDto.getSupplierNICNumber(),supplierDto.getSupplierContactNumber())));
    }

    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDao.delete(supplierId);
    }

    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException {
       return supplierDao.getAllSupplierIDs();
    }

    public SupplierDto findById(String selectedSupId) throws SQLException, ClassNotFoundException {
        return supplierDao.findById(selectedSupId);
    }
}
