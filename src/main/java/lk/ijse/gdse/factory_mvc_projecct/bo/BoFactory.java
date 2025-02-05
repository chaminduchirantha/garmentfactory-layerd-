package lk.ijse.gdse.factory_mvc_projecct.bo;

import lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl.*;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory() {

    }
    public static BoFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }
    public enum BoType {
        EMPLOYEE , SALARY , WORKSHEET, MACHINE, ATTENDANCE, SUPPLIER, PAYMENT,STOCK , BRANCH , USER, PRODUCT;
    }

    public SuperBo getBoType(BoType boType) {
        switch (boType) {
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case SALARY:
                return new SalaryBoImpl();
            case WORKSHEET:
                return new WorksheetBoImpl();
            case MACHINE:
                return new MachineBoImpl();
            case ATTENDANCE:
                return new AttendanceBoImpl();
                case SUPPLIER:
                    return new SupplierBoImpl();
                    case PAYMENT:
                        return new PaymentBoImpl();
                        case STOCK:
                            return new StockBoImpl();
                            case BRANCH:
                                return new BranchBoImpl();
                                case USER:
                                    return new UserBoImpl();
                                    case PRODUCT:
                                        return new ProductBoImpl();
        }
        return null;
    }
}
