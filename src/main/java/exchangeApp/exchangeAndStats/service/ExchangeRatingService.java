package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.ExchangeRating;

import java.util.List;

public interface ExchangeRatingService {
    void add(ExchangeRating rating);

    List<ExchangeRating> getRating();
}
