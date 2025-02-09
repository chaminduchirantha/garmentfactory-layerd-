package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.WorksheetDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.WorkSheet;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.WorksheetBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.WorkSheetSheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class WorksheetBoImpl implements WorksheetBo {
    WorksheetDao worksheetDao = (WorksheetDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.WORKSHEET);
    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return worksheetDao.getNextId();
    }

    @Override
    public boolean save(WorkSheetSheduleDto workSheetSheduleDto) throws SQLException, ClassNotFoundException {
        return worksheetDao.save(new WorkSheet(workSheetSheduleDto.getWorkSheetId(),workSheetSheduleDto.getWorkStartTime(),workSheetSheduleDto.getWorkEndTime(), workSheetSheduleDto.getTaskName(),workSheetSheduleDto.getEmployeeId()));
    }

    @Override
    public ArrayList<WorkSheetSheduleDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<WorkSheetSheduleDto> workSheetSheduleDtos = new ArrayList<>();
        ArrayList<WorkSheet> workSheets = worksheetDao.getAll();
        for (WorkSheet workSheet : workSheets) {
            workSheetSheduleDtos.add(new WorkSheetSheduleDto(workSheet.getWorkSheetId(),workSheet.getWorkStartTime(),workSheet.getWorkEndTime(), workSheet.getTaskName(),workSheet.getEmployeeId()));
        }
        return workSheetSheduleDtos;
    }

    @Override
    public boolean update(WorkSheetSheduleDto workSheetSheduleDto) throws SQLException, ClassNotFoundException {
        return worksheetDao.update(new WorkSheet(workSheetSheduleDto.getWorkSheetId(),workSheetSheduleDto.getWorkStartTime(),workSheetSheduleDto.getWorkEndTime(), workSheetSheduleDto.getTaskName(),workSheetSheduleDto.getEmployeeId()));
    }

    @Override
    public boolean delete(String workSheetId) throws SQLException, ClassNotFoundException {
        return worksheetDao.delete(workSheetId);
    }

    @Override
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException {
        return employeeDao.getAllEmployeeContactNumbers();
    }

    @Override
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException{
        return employeeDao.findByContactNumber(selectedEmId);
    }
}
