package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttendenceTm {
    private String attendenceId;
    private String entryTime;
    private String exitTime;
    private LocalDate attendenceDate;
    private String shiftType;
    private String employeeId;


}
