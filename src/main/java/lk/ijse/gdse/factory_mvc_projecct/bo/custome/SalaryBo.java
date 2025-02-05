package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException ;
    public boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException ;
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean update(SalaryDto salaryDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String salaryId) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException ;
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException;
}
