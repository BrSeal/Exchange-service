package exchangeApp.rates;

import exchangeApp.rates.DTO.CurrentRatesWithBaseDTO;
import exchangeApp.rates.entity.Rates;

public interface RatesService {
    CurrentRatesWithBaseDTO getActualRates(String base);

    Rates getCurrentRate();
}
