package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.MachineDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Machine;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineDaoImpl implements MachineDao {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "select machine_id from machines order by machine_id desc limit 1");
        if (resultSet.next()) {
            String machineId = resultSet.getString(1);
            String subString = machineId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("M%03d", newIndex);
        }
        return "M001";
    }

    @Override
    public ArrayList<Machine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from machines");
        ArrayList<Machine> machines = new ArrayList<>();

        while (rst.next()){
            Machine entity = new Machine();
                entity.setMachineId(rst.getString("machine_id"));
                entity.setMachineName(rst.getString("machine_name"));
                entity.setMachineTask(rst.getString("machine_task"));
                entity.setMachineQuantity(rst.getInt("machine_quantity"));
                entity.setEmployeeId(rst.getString("employee_id"));

            machines.add(entity);
        }
        return machines;
    }

    @Override
    public boolean save(Machine entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into machines values(?,?,?,?,?)", entity.getMachineId(),entity.getMachineName(),entity.getMachineTask(),entity.getMachineQuantity(),entity.getEmployeeId());
    }

    @Override
    public boolean update(Machine entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update machines set machine_name=?, machine_task=?,machine_quantity=?, employee_id=? where machine_id=?", entity.getMachineName(),entity.getMachineTask(),entity.getMachineQuantity(),entity.getEmployeeId(),entity.getMachineId());
    }

    @Override
    public boolean delete(String machineId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from machines where machine_id=?", machineId);
    }
}
