package exchangeApp.security.service;

import exchangeApp.security.entity.User;

public interface UserService {
    User get(String username);

    void save(User user);
}
