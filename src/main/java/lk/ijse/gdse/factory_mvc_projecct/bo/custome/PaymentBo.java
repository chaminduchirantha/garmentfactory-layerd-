package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.PaymentDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException;
    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean save(PaymentDto paymentDto) throws SQLException, ClassNotFoundException ;
    public boolean update(PaymentDto paymentDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException ;
    public SupplierDto findById(String selectedSupId) throws SQLException, ClassNotFoundException ;
}
