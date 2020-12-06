package exchangeApp.stats.exchangeRating;

import exchangeApp.stats.exchangeRating.entity.ExchangeRating;

import java.util.List;

public interface ExchangeRatingService {
    void add(ExchangeRating rating);

    List<ExchangeRating> getRating();
}
