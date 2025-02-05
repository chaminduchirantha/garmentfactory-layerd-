package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String paymentId;
    private LocalDate paymentDate;
    private double paymentAmount;
    private double discount;
    private String supplierId;
}
