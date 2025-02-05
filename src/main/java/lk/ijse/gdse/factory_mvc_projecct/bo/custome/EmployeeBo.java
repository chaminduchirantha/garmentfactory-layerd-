package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException ;
    public boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException ;
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean update(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException ;

}
