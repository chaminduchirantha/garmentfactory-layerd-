package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryDto {
    private String salaryId;
    private String salaryFees;
    private LocalDate salaryReleaseDate;
    private String basicSalary;
    private String paymentMethod;
    private String employeeId;
}
