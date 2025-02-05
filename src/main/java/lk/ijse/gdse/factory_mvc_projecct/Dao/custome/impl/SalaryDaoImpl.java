package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SalaryDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Salary;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;

import java.sql.*;
import java.util.ArrayList;

public class SalaryDaoImpl implements SalaryDao {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SqlUtil.execute( "select salary_id from salary order by salary_id desc limit 1");
        if (resultSet.next()) {
            String salaryId = resultSet.getString(1);
            String subString = salaryId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("S%03d", newIndex);
        }
        return "S001";
    }

    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into salary values(?,?,?,?,?,?)",entity.getSalaryId(),entity.getSalaryFees(),entity.getSalaryReleaseDate(),entity.getBasicSalary(),entity.getPaymentMethod(),entity.getEmployeeId());
    }

    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from salary");
        ArrayList<Salary> salaries = new ArrayList<>();

        while (rst.next()){
            Salary entity = new Salary();
                entity.setSalaryId(rst.getString("salary_id"));
                entity.setSalaryFees(rst.getString("salary_fees"));
                entity.setSalaryReleaseDate(rst.getDate("salary_release_date").toLocalDate());
                entity.setBasicSalary(rst.getString("basic_salary"));
                entity.setPaymentMethod(rst.getString("payment_method"));
                entity.setEmployeeId(rst.getString("employee_id"));

            salaries.add(entity);
        }
        return salaries;
    }

    @Override
    public boolean update(Salary entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update salary set salary_fees=? , salary_release_date=?,basic_salary=?,payment_method=?, employee_id=? where salary_id=?",entity.getSalaryFees(),entity.getSalaryReleaseDate(),entity.getBasicSalary(),entity.getPaymentMethod(),entity.getEmployeeId(),entity.getSalaryId());
    }

    @Override
    public boolean delete(String salaryId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from salary where salary_id=?", salaryId);
    }
}
