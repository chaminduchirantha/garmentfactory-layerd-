package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MachineTm {
    private String machineId;
    private String machineName;
    private String machineTask;
    private int machineQuantity;
    private String employeeId;
}
