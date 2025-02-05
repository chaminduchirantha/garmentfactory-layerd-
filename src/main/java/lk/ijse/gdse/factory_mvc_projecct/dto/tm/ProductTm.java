package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductTm {
    private String productId;
    private String productName;
    private double productPrice;
    private LocalDate productDate;
    private String productRatings;
}
