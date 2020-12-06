package exchangeApp.security;

import exchangeApp.security.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    @Query(value = "SELECT e.user FROM Exchange e GROUP BY e.user HAVING SUM(e.amountInUsd)>=:amount ")
    List<User> getUsersExchangedMoreInTotal(@Param("amount") double amount);

    @Query(value = "SELECT e.user FROM Exchange e WHERE e.amountInUsd>=:amount")
    List<User> getExchangedMoreThanAtOnes(@Param("amount") double amount);

    boolean existsByUsername(String username);
}
