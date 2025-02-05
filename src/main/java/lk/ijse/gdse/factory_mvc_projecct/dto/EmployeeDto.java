package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeDto {
    private String employeeId;
    private String employeeName;
    private int employeeAge;
    private String employeeAddress;
    private String employeeSection;
    private String employeeNic;
    private String employeeContactNumber;
}