package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public class WorkSheetSheduleTm {
        private String workSheetId;
        private String workStartTime;
        private String workEndTime;
        private String taskName;
        private String employeeId;
    }

