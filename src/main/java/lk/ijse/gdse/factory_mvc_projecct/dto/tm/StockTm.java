package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockTm {
    private String itemCode;
    private double itemPrice;
    private int quantity;
    private String itemDescription;
    private String itemQuality;

}
