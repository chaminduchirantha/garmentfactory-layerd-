package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBo extends SuperBo {

    public String getNextId() throws SQLException, ClassNotFoundException ;

    public ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException ;

    public boolean save(UserDto userDto) throws SQLException, ClassNotFoundException ;

    public boolean update(UserDto userDto) throws SQLException, ClassNotFoundException ;

    public boolean delete(String userId) throws SQLException, ClassNotFoundException ;
}
