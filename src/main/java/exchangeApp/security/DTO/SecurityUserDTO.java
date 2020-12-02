package exchangeApp.security.DTO;

import exchangeApp.security.entity.Authority;
import exchangeApp.security.entity.Role;
import exchangeApp.security.entity.User;
import lombok.Data;

import java.util.Set;

@Data
public class SecurityUserDTO {
    private String username;
    private String password;

    public User toUser(){
        User user=new User(username,password,true,null);
        Set<Authority> authorities=Set.of(new Authority(0,user, Role.ROLE_USER));
        user.setAuthorities(authorities);
        return user;
    }
}
