package lk.ijse.gdse.factory_mvc_projecct.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    private String supplierId;
    private String supplierName;
    private String supplierAddress;
    private int supplierAge;
    private String supplierContactNumber;
    private String supplierNICNumber;

}
