package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MachineBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException ;
    public ArrayList<MachineDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean save(MachineDto machineDto) throws SQLException, ClassNotFoundException ;
    public boolean update(MachineDto machineDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String machineId) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException ;
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException;
}
