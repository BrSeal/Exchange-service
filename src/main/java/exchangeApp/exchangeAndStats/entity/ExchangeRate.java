package exchangeApp.exchangeAndStats.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;
import java.util.TreeMap;

public class ExchangeRate {
    private final String base;

    Map<String,Double> rates;

    public ExchangeRate(String base) {
        this.base = base;
        rates = new TreeMap<>();
    }

    @JsonAnyGetter
    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
