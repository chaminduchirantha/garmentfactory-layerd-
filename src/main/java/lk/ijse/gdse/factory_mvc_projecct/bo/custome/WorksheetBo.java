package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.WorkSheetSheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WorksheetBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException ;
    public boolean save(WorkSheetSheduleDto workSheetSheduleDto) throws SQLException, ClassNotFoundException ;
    public ArrayList<WorkSheetSheduleDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean update(WorkSheetSheduleDto workSheetSheduleDto) throws SQLException, ClassNotFoundException ;
    public boolean delete(String workSheetId) throws SQLException, ClassNotFoundException ;
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException ;
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException;
}
