package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.UserDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.User;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst= SqlUtil.execute("select userId from user order BY userId desc limit 1");
        if(rst.next()){
            return String.format("U%03d", Integer.parseInt(rst.getString(1).substring(1))+1);
        }
        return "U001";
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from User");
        ArrayList<User> useres = new ArrayList<>();

        while (rst.next()){
            User entity = new User();
            entity.setUserId(rst.getString("user_id"));
            entity.setUserName(rst.getString("user_name"));
            entity.setUserPassword(rst.getString("user_Password"));
            entity.setUserEmail(rst.getString("user_Email"));
            entity.setUserContactNumber(rst.getString("user_contact_number"));

            useres.add(entity);
        }
        return useres;
    }


    @Override
    public boolean save(User entity) throws SQLException, ClassNotFoundException {
       return SqlUtil.execute( "insert into user values(?,?,?,?,?)", entity.getUserId(), entity.getUserName(), entity.getUserPassword(),entity.getUserEmail(), entity.getUserContactNumber());
    }


    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update user set user_name=?,user_password=?,user_email=?,use_contact_number=? where user_id=?",entity.getUserName(),entity.getUserPassword(),entity.getUserEmail(),entity.getUserContactNumber(),entity.getUserId());
    }


    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from user where user_id=?", userId);
    }
}
