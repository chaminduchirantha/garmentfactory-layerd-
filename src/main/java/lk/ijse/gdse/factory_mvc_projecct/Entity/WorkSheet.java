package lk.ijse.gdse.factory_mvc_projecct.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkSheet {
    private String workSheetId;
    private String workStartTime;
    private String workEndTime;
    private String taskName;
    private String employeeId;
}
