package exchangeApp.security.DTO;

import exchangeApp.security.entity.Authority;
import exchangeApp.security.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Set;

import static exchangeApp.security.entity.Role.*;

@Data
public class SecurityUserDTO {
    private String username;
    private String password;

    public User toUser(){
        User user=new User(username,password,true,null,new ArrayList<>());
        Set<Authority> authorities=Set.of(new Authority(0,user, ROLE_USER));
        user.setAuthorities(authorities);
        return user;
    }
}
