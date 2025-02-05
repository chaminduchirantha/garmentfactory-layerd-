package lk.ijse.gdse.factory_mvc_projecct.dto;

import lk.ijse.gdse.factory_mvc_projecct.Entity.Machine;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MachineDto extends Machine {
    private String machineId;
    private String machineName;
    private String machineTask;
    private int machineQuantity;
    private String employeeId;
}
