package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.BranchDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.Branch;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.BranchBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.BranchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class BranchBoImpl implements BranchBo {
    BranchDao branchDao = (BranchDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.BRANCH);
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
       return branchDao.getNextId();
    }

    @Override
    public ArrayList<BranchDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<BranchDto>branchDtos = new ArrayList<>();
        ArrayList<Branch>branches = branchDao.getAll();
        for (Branch branch : branches) {
            branchDtos.add(new BranchDto(branch.getBranchId(),branch.getBranchName(),branch.getBranchLocation(),branch.getNumberOfEmployee(),branch.getItemCode()));
        }
        return branchDtos;
    }

    @Override
    public boolean save(BranchDto branchDto) throws SQLException, ClassNotFoundException {
        return branchDao.save(new Branch(branchDto.getBranchId(),branchDto.getBranchName(),branchDto.getBranchLocation(),branchDto.getNumberOfEmployee(),branchDto.getItemCode()));
    }

    @Override
    public boolean update(BranchDto branchDto) throws SQLException, ClassNotFoundException {
        return branchDao.update(new Branch(branchDto.getBranchId(),branchDto.getBranchName(),branchDto.getBranchLocation(),branchDto.getNumberOfEmployee(),branchDto.getItemCode()));
    }

   @Override
    public boolean delete(String branchId) throws SQLException, ClassNotFoundException {
        return branchDao.delete(branchId);
    }
}
