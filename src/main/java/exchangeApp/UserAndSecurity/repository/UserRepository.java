package exchangeApp.UserAndSecurity.repository;

import exchangeApp.UserAndSecurity.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    @Query(value = "SELECT u, SUM(e.amountInUsd) AS total FROM User u " +
            "JOIN Exchange e ON u.username=e.username GROUP BY total HAVING total>?1")
    List<User> findUsersByExchangeSum(double amount);

    @Query(value = "SELECT u, SUM(e.amountInUsd) AS total FROM User u " +
            "JOIN Exchange e ON u.username=e.username GROUP BY total HAVING total>?1")
    List<User> findUsersByMinExchangeAmount(double amount);

    boolean existsByUsername(String username);
}
