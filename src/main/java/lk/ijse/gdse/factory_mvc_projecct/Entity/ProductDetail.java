package lk.ijse.gdse.factory_mvc_projecct.Entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetail {
    private String itemId;
    private String productId;
    private LocalDate productDate;
    private double itemPrice;
    private int itemQuantity;
    private String itemName;
    private double totalPrice;

}
