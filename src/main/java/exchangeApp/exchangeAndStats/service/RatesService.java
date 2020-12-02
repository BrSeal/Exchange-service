package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.CurrentRatesWithBaseDTO;
import exchangeApp.exchangeAndStats.entity.Rates;

import java.util.Map;

public interface RatesService {
    CurrentRatesWithBaseDTO getActualRates(String base);

    Rates getCurrentRate();
}
