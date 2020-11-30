package exchangeApp.exchangeAndStats.repository;

import exchangeApp.exchangeAndStats.entity.ExchangeRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRatingRepository extends CrudRepository<ExchangeRating,Integer> {

    boolean existsAllByFromAndTo(String from,String to);

    ExchangeRating findByFromEqualsAndToEquals(String from,String to);

    List<ExchangeRating> findByOrderByFrequency();
}
