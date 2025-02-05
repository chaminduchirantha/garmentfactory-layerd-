package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailDto {
    private String itemId;
    private String productId;
    private LocalDate productDate;
    private double itemPrice;
    private int itemQuantity;
    private String itemName;
    private double totalPrice;

}
