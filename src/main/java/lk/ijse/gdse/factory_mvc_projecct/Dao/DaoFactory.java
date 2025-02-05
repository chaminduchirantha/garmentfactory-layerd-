package lk.ijse.gdse.factory_mvc_projecct.Dao;

import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory() {

    }
    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public enum DaoType {
        EMPLOYEE , SALARY , WORKSHEET, MACHINE , ATTENDANCE, SUPPLIER, PAYMENT,STOCK, BRANCH , USER, SUPPLIERDETAIL, PRODUCTDETAIL;
    }

    public SuperDao getDao(DaoType type) {
        switch (type) {
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case SALARY:
                return new SalaryDaoImpl();
            case WORKSHEET:
                return new WorkSheetDaoImpl();
                case MACHINE:
                    return new MachineDaoImpl();
                    case ATTENDANCE:
                        return new AttendanceDaoImpl();
                        case SUPPLIER:
                            return new SupplierDaoImpl();
                            case PAYMENT:
                                return new PaymentDaoImpl();
                                case STOCK:
                                    return new StockDaoImpl();
                                    case BRANCH:
                                        return new BranchDaoImpl();
                                        case USER:
                                            return new UserDaoImpl();
                                            case SUPPLIERDETAIL:
                                                return new SupplierDetailsDaoImpl();
                                                case PRODUCTDETAIL:
                                                    return new ProductDetailDaoImpl();
        }
        return null;
    }
}
