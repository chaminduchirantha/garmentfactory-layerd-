package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.EmployeeDao;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.MachineDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Employee;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Machine;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.MachineBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.EmployeeDto;
import lk.ijse.gdse.factory_mvc_projecct.dto.MachineDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class MachineBoImpl implements MachineBo {

    MachineDao machineDao = (MachineDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.MACHINE);
    EmployeeDao employeeDao = (EmployeeDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return machineDao.getNextId();
    }

    @Override
    public ArrayList<MachineDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<MachineDto> machineDtos = new ArrayList<>();
        ArrayList<Machine>machines = machineDao.getAll();
        for (Machine machine : machines) {
            machineDtos.add(new MachineDto(machine.getMachineId(),machine.getMachineName(),machine.getMachineTask(),machine.getMachineQuantity(),machine.getEmployeeId()));
        }
        return machineDtos;
    }

    @Override
    public boolean save(MachineDto machineDto) throws SQLException, ClassNotFoundException {
        return machineDao.save(machineDto);
    }

    @Override
    public boolean update(MachineDto machineDto) throws SQLException, ClassNotFoundException {
        return machineDao.update(machineDto);
    }

    @Override
    public boolean delete(String machineId) throws SQLException, ClassNotFoundException {
        return machineDao.delete(machineId);
    }

    @Override
    public ArrayList<String> getAllEmployeeContactNumbers() throws SQLException, ClassNotFoundException {
        return employeeDao.getAllEmployeeContactNumbers();
    }

    @Override
    public EmployeeDto findByContactNumber(String selectedEmId) throws SQLException, ClassNotFoundException{
        Employee employee = employeeDao.findByContactNumber(selectedEmId);
        return new EmployeeDto(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeAge(),employee.getEmployeeAddress(),employee.getEmployeeSection(),employee.getEmployeeNic(),employee.getEmployeeContactNumber()) ;
    }
}
