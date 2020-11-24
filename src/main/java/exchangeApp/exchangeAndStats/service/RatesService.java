package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.Rates;

import java.util.Map;

public interface RatesService {
    Map<String, Double> getActualRates();

    Map<String, Double> getActualRates(String base);

    Rates getCurrentRate();
}
