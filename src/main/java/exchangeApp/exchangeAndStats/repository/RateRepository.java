package exchangeApp.exchangeAndStats.repository;

import exchangeApp.exchangeAndStats.entity.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate,Integer> {
}
