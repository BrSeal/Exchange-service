package exchangeApp.exchangeAndStats.repository;

import exchangeApp.exchangeAndStats.entity.Exchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange,Integer> {

    List<Exchange> findAllByUsername(String userId);
}
