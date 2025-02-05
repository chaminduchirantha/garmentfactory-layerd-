package lk.ijse.gdse.factory_mvc_projecct.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserTm {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userContactNumber;
}
