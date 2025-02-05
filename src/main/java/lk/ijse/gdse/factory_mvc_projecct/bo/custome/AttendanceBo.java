package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.AttendenceDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBo extends SuperBo {
    public  String getNextId() throws SQLException, ClassNotFoundException ;
    public ArrayList<AttendenceDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(AttendenceDto attendenceDto) throws SQLException, ClassNotFoundException;
    public boolean update(AttendenceDto attendenceDto) throws SQLException, ClassNotFoundException;
    public boolean delete(String AttendenceId) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException ;
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException;

}
