package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private String branchId;
    private String branchName;
    private String branchLocation;
    private int numberOfEmployee;
    private String ItemCode;
}
