package lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.BranchDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Branch;
import lk.ijse.gdse.factory_mvc_projecct.Dao.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDaoImpl implements BranchDao {
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute( "select branch_id from branch order by branch_id desc limit 1");
        if (resultSet.next()) {
            String branchId = resultSet.getString(1);
            String subString = branchId.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("B%03d", newIndex);
        }
        return "B001";
    }

    @Override
    public ArrayList<Branch> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =  SqlUtil.execute("select * from branch");
        ArrayList<Branch> branches = new ArrayList<>();

        while (rst.next()){
            Branch entity = new Branch();
            entity.setBranchId(rst.getString("branch_id"));
            entity.setBranchName(rst.getString("branch_name"));
            entity.setBranchLocation(rst.getString("branch_location"));
            entity.setNumberOfEmployee(rst.getInt("number_of_employee"));
            entity.setItemCode(rst.getString("item_id"));

            branches.add(entity);
        }
        return branches;
    }

    @Override
    public boolean save(Branch entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into branch values(?,?,?,?,?)", entity.getBranchId(), entity.getBranchName(), entity.getBranchLocation(), entity.getNumberOfEmployee(), entity.getItemCode());
    }


    @Override
    public boolean update(Branch entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("update branch set branch_name=?, branch_location=?, number_of_employee=?, item_id=? where branch_id=?",entity.getBranchName(),entity.getBranchLocation(),entity.getNumberOfEmployee(),entity.getItemCode(), entity.getBranchId());
    }


    @Override
    public boolean delete(String branchId) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from branch where branch_id=?", branchId);
    }
}
