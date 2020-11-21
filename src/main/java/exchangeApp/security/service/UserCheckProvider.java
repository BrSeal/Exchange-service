package exchangeApp.security.service;


import exchangeApp.security.entity.Authority;
import exchangeApp.security.entity.User;

import java.util.List;
import java.util.Set;

public class UserCheckProvider {
    private static final String USERNAME_IS_NULL_OR_EMPTY = "Username is null or empty!";
    private static final String USERNAME_IS_ALREADY_IN_USE = "Username is already in use!";
    private static final String PASSWORD_IS_NULL_OR_EMPTY = "Password is null or empty!";
    private static final String NO_AUTHORITIES = "No authorities presented for user %s!";
    private static final String AUTHORITY_CORRUPTED = "Authority is null or assigned to another user!";
    private static final String ROLE_IS_NULL = "Role is null!";
    private static final String NO_AUTHORITY = "Authority of the new user is null!";

    public void validateUser(User user, List<User> users) {
        ifExists(user);

        String username = user.getUsername();
        String password = user.getPassword();
        Set<Authority> authorities = user.getAuthorities();

        validateString(username,USERNAME_IS_NULL_OR_EMPTY);
        validateString(password,PASSWORD_IS_NULL_OR_EMPTY);

        if (users.stream().anyMatch(u -> u.getUsername().equals(username)))
            throw new IllegalArgumentException(USERNAME_IS_ALREADY_IN_USE);


        if (authorities == null || authorities.size() == 0) {
            String errMsg = String.format(NO_AUTHORITIES, username);
            throw new IllegalArgumentException(errMsg);
        }

        authorities.forEach(authority -> {
            if (authority == null) throw new IllegalArgumentException(NO_AUTHORITY);

            User authorityUser = authority.getUser();

            if (authorityUser == null || authorityUser != user) throw new IllegalArgumentException(AUTHORITY_CORRUPTED);
            if (authority.getRole() == null) throw new IllegalArgumentException(ROLE_IS_NULL);
        });
    }

    public void ifExists(User user) {
        if (user == null) throw new IllegalArgumentException(AUTHORITY_CORRUPTED);
    }


    private void validateString(String s, String errMsg){
        if (s == null || s.isEmpty()) throw new IllegalArgumentException(errMsg);
    }
}
