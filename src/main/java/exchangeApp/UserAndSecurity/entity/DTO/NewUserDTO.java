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
public class NewUserDTO {
    private String username;
    private String password;

    public User toUser(){
        return new User(username,password,true, null);
    }
}
