package lk.ijse.gdse.factory_mvc_projecct.Dao.custome;

import lk.ijse.gdse.factory_mvc_projecct.Dao.CrudDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDao extends CrudDao<Employee> {
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException;
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException;
}
