package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.PaymentDao;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SupplierDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Payment;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.PaymentBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.PaymentDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {
    PaymentDao paymentDao = (PaymentDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PAYMENT);
    SupplierDao supplierDao = (SupplierDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SUPPLIER);

    public String getNextId() throws SQLException, ClassNotFoundException{
        return paymentDao.getNextId();
    }

    public ArrayList<PaymentDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        ArrayList<Payment> payments = paymentDao.getAll();
        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(payment.getPaymentId(),payment.getPaymentDate(),payment.getDiscount(),payment.getPaymentAmount(),payment.getSupplierId()));
        }
        return paymentDtos;
    }

    public boolean save(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDao.save(new Payment(paymentDto.getPaymentId(),paymentDto.getPaymentDate(),paymentDto.getPaymentAmount(),paymentDto.getDiscount(),paymentDto.getSupplierId()));
    }

    public boolean update(PaymentDto paymentDto) throws SQLException, ClassNotFoundException {
        return paymentDao.update(new Payment(paymentDto.getPaymentId(),paymentDto.getPaymentDate(),paymentDto.getPaymentAmount(),paymentDto.getDiscount(),paymentDto.getSupplierId()));
    }

    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException {
        return paymentDao.delete(paymentId);
    }

    public ArrayList<String> getAllSupplierIDs() throws SQLException, ClassNotFoundException {
        return supplierDao.getAllSupplierIDs();
    }

    public SupplierDto findById(String selectedSupId) throws SQLException, ClassNotFoundException {
        return supplierDao.findById(selectedSupId);
    }
}
