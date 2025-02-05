package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.PaymentDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Payment;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;

import java.sql.*;
import java.util.ArrayList;

public class PaymentDaoImpl implements PaymentDao {
    public String getNextId() throws SQLException, ClassNotFoundException{
        ResultSet resultSet = SqlUtil.execute( "select payment_id from payment_management order by payment_id desc limit 1");
        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String subString = paymentId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("M%03d", newIndex);
        }
        return "M001";
    }

    public ArrayList<Payment> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from payment_management");
        ArrayList<Payment> payments = new ArrayList<>();

        while (rst.next()){
            Payment paymentDto = new Payment();
            paymentDto.setPaymentId(rst.getString("payment_id"));
            paymentDto.setPaymentDate(rst.getDate("payment_date").toLocalDate());
            paymentDto.setPaymentAmount(rst.getDouble("payment_Amount"));
            paymentDto.setDiscount(rst.getDouble("discount"));
            paymentDto.setSupplierId(rst.getString("supplier_id"));

            payments.add(paymentDto);
        }
        return payments;
    }

    public boolean save(Payment entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into payment_management values(?,?,?,?,?)",entity.getPaymentId(),entity.getPaymentDate(),entity.getPaymentAmount(),entity.getDiscount(),entity.getSupplierId());
    }

    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update payment_management set payment_date=?,discount=?,payment_Amount=?,supplier_id=? where payment_id=?",entity.getPaymentDate(),entity.getPaymentAmount(),entity.getDiscount(),entity.getSupplierId(),entity.getPaymentId());
    }

    public boolean delete(String paymentId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from payment_management where payment_id=?", paymentId);
    }
}
