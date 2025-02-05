package lk.ijse.gdse.factory_mvc_projecct.bo.custome;

import lk.ijse.gdse.factory_mvc_projecct.bo.SuperBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.BranchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BranchBo extends SuperBo {
    public String getNextId() throws SQLException, ClassNotFoundException;

    public ArrayList<BranchDto> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(BranchDto entity) throws SQLException, ClassNotFoundException;

    public boolean update(BranchDto entity) throws SQLException, ClassNotFoundException;

    public boolean delete(String branchId) throws SQLException, ClassNotFoundException;
}
