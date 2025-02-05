package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.WorksheetDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.WorkSheet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkSheetDaoImpl implements WorksheetDao {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "select shedule_id from worksheet_shedule order by shedule_id desc limit 1");
        if (resultSet.next()) {
            String workSheetId = resultSet.getString(1);
            String subString = workSheetId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("W%03d", newIndex);
        }
        return "W001";
    }

    @Override
    public boolean save(WorkSheet entity) throws SQLException, ClassNotFoundException {
       return SqlUtil.execute("insert into worksheet_shedule values(?,?,?,?,?)",entity.getWorkSheetId(),entity.getWorkStartTime(),entity.getWorkEndTime(),entity.getTaskName(),entity.getEmployeeId());
    }

    @Override
    public ArrayList<WorkSheet> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from worksheet_shedule");
        ArrayList<WorkSheet> workSheets = new ArrayList<>();

        while (rst.next()){
            WorkSheet entity = new WorkSheet();
                entity.setWorkSheetId(rst.getString("shedule_id"));
                entity.setWorkStartTime(rst.getString("shedule_start_time"));
                entity.setWorkEndTime(rst.getString("shedule_end_time"));
                entity.setTaskName(rst.getString("task_name"));
                entity.setEmployeeId(rst.getString("employee_id"));

            workSheets.add(entity);
        }
        return workSheets;
    }

    @Override
    public boolean update(WorkSheet entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update worksheet_shedule set shedule_start_time=?, shedule_end_time=?, task_name=?, employee_id=? where shedule_id=?",entity.getWorkStartTime(),entity.getWorkEndTime(),entity.getTaskName(),entity.getEmployeeId(),entity.getWorkSheetId());
    }

    @Override
    public boolean delete(String workSheetId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from worksheet_shedule where shedule_id=?", workSheetId);
    }
}
