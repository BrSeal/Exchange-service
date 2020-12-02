package exchangeApp.userPart.DTO;

import exchangeApp.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private String username;

    public UserInfoDTO(User user) {
        username = user.getUsername();
    }
}
