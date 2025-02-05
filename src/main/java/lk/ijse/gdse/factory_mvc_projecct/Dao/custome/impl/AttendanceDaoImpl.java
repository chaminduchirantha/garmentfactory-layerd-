package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.AttendanceDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDaoImpl implements AttendanceDao {
    public  String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "select attendence_id from attendence order by attendence_id desc limit 1");
        if (resultSet.next()) {
            String attendenceId = resultSet.getString(1);
            String subString = attendenceId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("A%03d", newIndex);
        }
        return "A001";
    }

    public ArrayList<Attendance> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from attendence");
        ArrayList<Attendance> attendances = new ArrayList<>();

        while (rst.next()){
            Attendance entity = new Attendance();
            entity.setAttendenceId(rst.getString("attendence_id"));
            entity.setEntryTime(rst.getString("entry_time"));
            entity.setExitTime(rst.getString("exite_time"));
            entity.setAttendenceDate(rst.getDate("attendence_date").toLocalDate());
            entity.setShiftType(rst.getString("shift_type"));
            entity.setEmployeeId(rst.getString("employee_id"));

            attendances.add(entity);
        }
        return attendances;
    }

    public boolean save(Attendance entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into attendence values(?,?,?,?,?,?)",entity.getAttendenceId(),entity.getEntryTime(),entity.getExitTime(),entity.getAttendenceDate(),entity.getShiftType(),entity.getEmployeeId());
    }

    public boolean update(Attendance entity) throws SQLException, ClassNotFoundException {
        return    SqlUtil.execute("update attendence set entry_time=?, exite_time=?, attendence_date=?, shift_type=?, employee_id=? where attendence_id=?", entity.getEntryTime(), entity.getExitTime(), entity.getAttendenceDate(), entity.getShiftType(), entity.getEmployeeId(), entity.getAttendenceId());
    }

    public boolean delete(String AttendenceId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from attendence where attendence_id=?", AttendenceId);
    }
}
