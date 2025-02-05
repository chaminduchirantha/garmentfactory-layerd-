module lk.ijse.gdse.garment_factory_layerd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires net.sf.jasperreports.core;
    requires java.desktop;


    opens lk.ijse.gdse.factory_mvc_projecct.dto.tm to javafx.base;
    opens lk.ijse.gdse.factory_mvc_projecct.controller to javafx.fxml;
    exports lk.ijse.gdse.factory_mvc_projecct;
}