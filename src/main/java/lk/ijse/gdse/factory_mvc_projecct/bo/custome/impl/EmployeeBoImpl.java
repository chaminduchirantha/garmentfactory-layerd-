package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.EmployeeBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBoImpl implements EmployeeBo {
    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return employeeDao.getNextId();
    }

    @Override
    public boolean save(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDao.save(new Employee(employeeDto.getEmployeeId(),employeeDto.getEmployeeName(),employeeDto.getEmployeeAge(),employeeDto.getEmployeeAddress(),employeeDto.getEmployeeSection(),employeeDto.getEmployeeNic(),employeeDto.getEmployeeContactNumber()));
    }

    @Override
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        ArrayList<Employee> employees = employeeDao.getAll();
        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAge(),employee.getEmployeeAddress(),employee.getEmployeeSection(),employee.getEmployeeNic(),employee.getEmployeeContactNumber()));

        }
        return employeeDtos;
    }

    @Override
    public boolean update(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return employeeDao.update(new Employee(employeeDto.getEmployeeId(),employeeDto.getEmployeeName(),employeeDto.getEmployeeAge(),employeeDto.getEmployeeAddress(),employeeDto.getEmployeeSection(),employeeDto.getEmployeeNic(),employeeDto.getEmployeeContactNumber()));
    }

    @Override
    public boolean delete(String employeeId) throws SQLException, ClassNotFoundException {
        return employeeDao.delete(employeeId);
    }
}
