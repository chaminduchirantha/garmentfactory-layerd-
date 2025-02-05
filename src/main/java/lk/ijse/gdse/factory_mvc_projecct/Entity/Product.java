package lk.ijse.gdse.factory_mvc_projecct.Entity;

import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String productName;
    private double productPrice;
    private LocalDate productDate;
    private String productRating;
    private ArrayList<ProductDetail> productDetailDtos;


}
