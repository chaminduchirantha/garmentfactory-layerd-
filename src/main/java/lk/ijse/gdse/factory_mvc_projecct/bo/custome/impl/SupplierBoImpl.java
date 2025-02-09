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

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return supplierDao.getNextId();
    }

    @Override
    public boolean save(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDao.save((new Supplier(supplierDto.getSupplierId(),supplierDto.getSupplierName(),supplierDto.getSupplierAddress(),supplierDto.getSupplierAge(),supplierDto.getSupplierContactNumber(),supplierDto.getSupplierNICNumber())));
    }

    @Override
    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier>suppliers = supplierDao.getAll();
        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierAddress(),supplier.getSupplierAge(),supplier.getSupplierContactNumber(),supplier.getSupplierNICNumber()));
        }
        return supplierDtos;
    }

    @Override
    public boolean update(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        return supplierDao.update((new Supplier(supplierDto.getSupplierId(),supplierDto.getSupplierName(),supplierDto.getSupplierAddress(),supplierDto.getSupplierAge(),supplierDto.getSupplierNICNumber(),supplierDto.getSupplierContactNumber())));
    }

    @Override
    public boolean delete(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDao.delete(supplierId);
    }

    @Override
    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException {
       return supplierDao.getAllSupplierIDs();
    }

    @Override
    public SupplierDto findById(String selectedSupId) throws SQLException, ClassNotFoundException {
        return supplierDao.findById(selectedSupId);
    }
}
