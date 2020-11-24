package exchangeApp.UserAndSecurity.service;

import exchangeApp.UserAndSecurity.entity.DTO.NewUserDTO;
import exchangeApp.UserAndSecurity.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserCheckProvider {
    private static final String NULL_USER = "User is not presented!";
    private static final String ALREADY_EXISTS = "User with this username already exists!";
    private static final String NAME_NULL = "Name is not presented!";
    private static final String PASS_NULL = "Password is not presented!";
    private static final String NAME_BAD_LENGTH = "Name must be 3 to 50 characters long!";
    private static final String PASS_BAD_LENGTH = "Password must be 3 to 50 characters long!";

        private final UserRepository repository;

    public UserCheckProvider(UserRepository repository) {
        this.repository = repository;
    }

    public void validateNew(NewUserDTO user) {
        if(user==null) throw new IllegalArgumentException(NULL_USER);
        isValidNamePass(user.getUsername(),user.getPassword());
        if(repository.existsByUsername(user.getUsername())) throw new IllegalArgumentException(ALREADY_EXISTS);
    }

    private void isValidNamePass(String name, String password){
        if(name==null) throw new IllegalArgumentException(NAME_NULL);
        if(password==null) throw new IllegalArgumentException(PASS_NULL);
        if(name.length()<3||name.length()>50) throw new IllegalArgumentException(NAME_BAD_LENGTH);
        if(password.length()<3||password.length()>50) throw new IllegalArgumentException(PASS_BAD_LENGTH);
    }

}
