package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.UserDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.User;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.UserBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBo {
    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.USER);

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return userDao.getNextId();
    }

    @Override
    public ArrayList<UserDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<UserDto>userDtos = new ArrayList<>();
        ArrayList<User>users = userDao.getAll();
        for (User user : users) {
            userDtos.add(new UserDto(user.getUserId(),user.getUserName(),user.getUserPassword(),user.getUserEmail(),user.getUserContactNumber()));
        }
        return userDtos;
    }

    @Override
    public boolean save(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDao.save(new User(userDto.getUserId(),userDto.getUserName(),userDto.getUserPassword(),userDto.getUserEmail(),userDto.getUserContactNumber()));
    }

    @Override
    public boolean update(UserDto userDto) throws SQLException, ClassNotFoundException {
        return userDao.save(new User(userDto.getUserId(),userDto.getUserName(),userDto.getUserPassword(),userDto.getUserEmail(),userDto.getUserContactNumber()));
    }

    @Override
    public boolean delete(String userId) throws SQLException, ClassNotFoundException {
        return userDao.delete(userId);
    }
}
