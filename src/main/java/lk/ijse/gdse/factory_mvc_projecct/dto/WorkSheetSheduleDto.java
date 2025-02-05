package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkSheetSheduleDto {
    private String workSheetId;
    private String workStartTime;
    private String workEndTime;
    private String taskName;
    private String employeeId;
}
