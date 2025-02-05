package lk.ijse.gdse.factory_mvc_projecct.Entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {
    private String attendenceId;
    private String entryTime;
    private String exitTime;
    private LocalDate attendenceDate;
    private String shiftType;
    private String employeeId;

}
