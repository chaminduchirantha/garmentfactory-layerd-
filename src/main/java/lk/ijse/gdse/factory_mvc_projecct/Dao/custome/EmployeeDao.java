package lk.ijse.gdse.factory_mvc_projecct.Dao.custome;

import lk.ijse.gdse.factory_mvc_projecct.Dao.CrudDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDao extends CrudDao<Employee> {
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException;
    public Employee findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException;
}
