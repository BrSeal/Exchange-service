package exchangeApp.exchange;

import exchangeApp.exchange.entity.Exchange;
import exchangeApp.security.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange,Integer> {
    List<Exchange> findAllByUser(User user);
}
