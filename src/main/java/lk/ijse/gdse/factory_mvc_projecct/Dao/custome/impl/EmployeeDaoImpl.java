package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDaoImpl implements EmployeeDao {
@Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "select employee_id from employee order by employee_id desc limit 1");
        if (resultSet.next()) {
            String employeeId = resultSet.getString(1); //E005
            String subString = employeeId.substring(1); //5 String
            int i = Integer.parseInt(subString); //5 int
            int newIndex = i+1; //6
            return String.format("E%03d", newIndex); //E006
        }
        return "E001";
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
       return SqlUtil.execute("insert into employee values(?,?,?,?,?,?,?)",
               entity.getEmployeeId(),
               entity.getEmployeeName(),
               entity.getEmployeeAge(),
               entity.getEmployeeAddress(),
               entity.getEmployeeSection(),
               entity.getEmployeeNic(),
               entity.getEmployeeContactNumber()
       );
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from employee");
        ArrayList<Employee> employees = new ArrayList<>();

        while (rst.next()){
            Employee entity = new Employee();
            entity.setEmployeeId(rst.getString("employee_id"));
            entity.setEmployeeName(rst.getString("employee_name"));
            entity.setEmployeeAge(rst.getInt("employee_age"));
            entity.setEmployeeAddress(rst.getString("employee_address"));
            entity.setEmployeeSection(rst.getString("employee_section"));
            entity.setEmployeeNic(rst.getString("employee_nic_number"));
            entity.setEmployeeContactNumber(rst.getString("employee_contact_number"));

            employees.add(entity);
        }
        return employees;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update employee set employee_name=?, employee_age=?, employee_address=?, employee_section=?, employee_nic_number=?, employee_contact_number=? where employee_id=?",
                entity.getEmployeeName(),
                entity.getEmployeeAge(),
                entity.getEmployeeAddress(),
                entity.getEmployeeSection(),
                entity.getEmployeeNic(),
                entity.getEmployeeContactNumber(),
                entity.getEmployeeId()
        );
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from employee where employee_id=?", employeeId);
    }

    @Override
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("select employee_contact_number from employee");
        ArrayList<String> employeeIds = new ArrayList<>();
        while (rst.next()) {
            employeeIds.add(rst.getString(1));
        }
        return employeeIds;
    }

    @Override
    public Employee findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("select * from employee where employee_contact_number=?", selectedEmId);

        if (rst.next()) {
            return new Employee(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7));
        }
        return null;
    }
}