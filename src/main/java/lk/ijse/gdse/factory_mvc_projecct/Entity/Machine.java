package lk.ijse.gdse.factory_mvc_projecct.Entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Machine {
    private String machineId;
    private String machineName;
    private String machineTask;
    private int machineQuantity;
    private String employeeId;
}
