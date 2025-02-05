package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.SupplierDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException ;
    public boolean save(SupplierDto supplierDto) throws SQLException, ClassNotFoundException ;
    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean update(SupplierDto supplierDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String entity) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException;
    public SupplierDto findById(String selectedSupId) throws SQLException, ClassNotFoundException;
}
