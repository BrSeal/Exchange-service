package exchangeApp.stats.exchangeRating;

import exchangeApp.stats.DTO.ExchangeRatingDto;
import exchangeApp.stats.exchangeRating.entity.ExchangeRating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<ExchangeRatingDto> getRating() {
        return repository.findByOrderByFrequencyDesc().stream()
                .map(ExchangeRatingDto::new)
                .collect(Collectors.toList());
    }
}
