package exchangeApp.UserAndSecurity.entity.DTO;

import exchangeApp.UserAndSecurity.entity.User;
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
    private String password;
    private boolean enabled;

    public UserInfoDTO(User user) {
        username = user.getUsername();
        password = user.getPassword();
        enabled = user.isEnabled();
    }
}
