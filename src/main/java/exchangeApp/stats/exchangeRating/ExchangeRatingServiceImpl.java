package exchangeApp.stats.exchangeRating;

import exchangeApp.stats.exchangeRating.entity.ExchangeRating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExchangeRatingServiceImpl implements ExchangeRatingService{
    private final ExchangeRatingRepository repository;

    @Override
    public void add(ExchangeRating rating) {
        String from=rating.getFrom();
        String to=rating.getTo();

        if(repository.existsAllByFromAndTo(from,to)) {
            rating = repository.findByFromEqualsAndToEquals(from, to);
            rating.setFrequency(rating.getFrequency()+1);
        }
        repository.save(rating);
    }

    @Override
    public List<ExchangeRating> getRating() {
        return repository.findByOrderByFrequency();
    }
}
