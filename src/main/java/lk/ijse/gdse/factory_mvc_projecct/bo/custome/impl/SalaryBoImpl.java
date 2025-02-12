package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.SalaryDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Salary;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.SalaryBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBoImpl implements SalaryBo {

    SalaryDao salaryDao = (SalaryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.SALARY);
    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return salaryDao.getNextId();
    }

    @Override
    public boolean save(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        return salaryDao.save(new Salary(salaryDto.getSalaryId(),salaryDto.getSalaryFees(),salaryDto.getSalaryReleaseDate(),salaryDto.getBasicSalary(),salaryDto.getPaymentMethod(),salaryDto.getEmployeeId()));
    }

    @Override
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SalaryDto> salaryDtos = new ArrayList<>();
        ArrayList<Salary> salaries = salaryDao.getAll();
        for (Salary salary : salaries) {
            salaryDtos.add(new SalaryDto(salary.getSalaryId(),salary.getSalaryFees(),salary.getSalaryReleaseDate(),salary.getBasicSalary(),salary.getPaymentMethod(),salary.getEmployeeId()));
        }
        return salaryDtos;

    }

    @Override
    public boolean update(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        return salaryDao.update(new Salary(salaryDto.getSalaryId(),salaryDto.getSalaryFees(),salaryDto.getSalaryReleaseDate(),salaryDto.getBasicSalary(),salaryDto.getPaymentMethod(),salaryDto.getEmployeeId()));
    }

    @Override
    public boolean delete(String salaryId) throws SQLException, ClassNotFoundException {
        return salaryDao.delete(salaryId);
    }

    @Override
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException {
        return employeeDao.getAllEmployeeContactNumbers();
    }

    @Override
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException{
        Employee employee = employeeDao.findByContactNumber(selectedEmId);
        return new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAge(),employee.getEmployeeAddress(),employee.getEmployeeSection(),employee.getEmployeeNic(),employee.getEmployeeContactNumber());
    }
}
