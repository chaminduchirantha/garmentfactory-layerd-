package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockDto {
    private String itemCode;
    private double itemPrice;
    private int quantity;
    private String itemDescription;
    private String itemQuality;

}
