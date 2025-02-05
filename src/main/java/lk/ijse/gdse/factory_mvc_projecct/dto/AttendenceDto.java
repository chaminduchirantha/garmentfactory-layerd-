package lk.ijse.gdse.factory_mvc_projecct.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Locale;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AttendenceDto {
    private String attendenceId;
    private String entryTime;
    private String exitTime;
    private LocalDate attendenceDate;
    private String shiftType;
    private String employeeId;

}
