package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartTm {
    private String itemId;
    private double itemPrice;
    private int itemQuantity;
    private String itemName;
    private double totalPrice;
}
