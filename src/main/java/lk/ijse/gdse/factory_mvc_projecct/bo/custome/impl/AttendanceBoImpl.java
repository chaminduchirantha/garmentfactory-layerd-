package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.AttendanceDao;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Attendance;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.AttendanceBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.AttendenceDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBoImpl implements AttendanceBo {
    AttendanceDao attendanceDao = (AttendanceDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.ATTENDANCE);
    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public  String getNextId() throws SQLException, ClassNotFoundException {
        return attendanceDao.getNextId();

    }

    @Override
    public ArrayList<AttendenceDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<AttendenceDto> attendenceDtos = new ArrayList<>();
        ArrayList<Attendance> attendances = attendanceDao.getAll();
        for (Attendance attendance : attendances) {
            attendenceDtos.add(new AttendenceDto(attendance.getAttendenceId(),attendance.getEntryTime(),attendance.getExitTime(),attendance.getAttendenceDate(),attendance.getShiftType(),attendance.getEmployeeId()));
        }
        return attendenceDtos;
    }

    @Override
    public boolean save(AttendenceDto attendenceDto) throws SQLException, ClassNotFoundException {
        return attendanceDao.save(new Attendance(attendenceDto.getAttendenceId(),attendenceDto.getEntryTime(),attendenceDto.getExitTime(),attendenceDto.getAttendenceDate(),attendenceDto.getShiftType(),attendenceDto.getEmployeeId()));
    }

    @Override
    public boolean update(AttendenceDto attendenceDto) throws SQLException, ClassNotFoundException {
        return attendanceDao.update(new Attendance(attendenceDto.getAttendenceId(),attendenceDto.getEntryTime(),attendenceDto.getExitTime(),attendenceDto.getAttendenceDate(),attendenceDto.getShiftType(),attendenceDto.getEmployeeId()));
    }

    @Override
    public boolean delete(String AttendenceId) throws SQLException, ClassNotFoundException {
       return attendanceDao.delete(AttendenceId);
    }


    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException {
        return employeeDao.getAllEmployeeContactNumbers();
    }

    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException{
        Employee employee = employeeDao.findByContactNumber(selectedEmId);
        return new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAge(),employee.getEmployeeAddress(),employee.getEmployeeSection(),employee.getEmployeeNic(),employee.getEmployeeContactNumber());
    }
}
