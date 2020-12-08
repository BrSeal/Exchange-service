package exchangeApp.rates;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchangeApp.exchange.CheckProvider;
import exchangeApp.rates.DTO.CurrentRatesWithBaseDTO;
import exchangeApp.rates.DTO.ExternalApiResponse;
import exchangeApp.rates.entity.Rates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class RatesServiceImpl implements RatesService {
    private static final String STANDARD_TYPE = "USD";

    private final CheckProvider check;
    private final RateRepository rateRepository;
    private Map<String, Double> ratesMap;

    private Rates currentRates;

    @Value("${ratesUpdateTime.HOURS}")
    private int UPDATE_TIME_HOURS;

    @Value("${ratesUpdateTime.MINUTES}")
    private int UPDATE_TIME_MINUTES;

    @Value("${getRatesUrl}")
    private String GET_RATES_URL;

    public RatesServiceImpl(CheckProvider check, RateRepository rateRepository) {
        this.check = check;
        this.rateRepository = rateRepository;
    }

    @Override
    public CurrentRatesWithBaseDTO getActualRates(String base) {
        refreshRatesIfNeeded();

        if (base==null|| base.length()==0||base.equals(STANDARD_TYPE)) base=STANDARD_TYPE;
        else {
            base=base.toUpperCase();
            check.validateCurrencyType(base, ratesMap);
        }

        double rateToUsd = ratesMap.get(base);
        Map<String, Double> ratesByBase=new HashMap<>();
        ratesMap.forEach((k, v) -> ratesByBase.put(k, v / rateToUsd));

        return new CurrentRatesWithBaseDTO(base,ratesByBase);
    }

    private void refreshRatesIfNeeded() {
        if (ratesMap == null) requestRatesFromExternalAPI();

        else {
            Calendar current = Calendar.getInstance();
            Calendar fromRate = new GregorianCalendar();
            fromRate.setTime(currentRates.getDate());
            fromRate.add(Calendar.HOUR, 24);

            if (current.compareTo(fromRate) > 0) requestRatesFromExternalAPI();
        }
    }

    private void requestRatesFromExternalAPI() {
        try {
            ratesMap = new RestTemplate().getForObject(GET_RATES_URL, ExternalApiResponse.class)
                    .getRates();

            Calendar current = Calendar.getInstance();
            String jsonRatesMap = new ObjectMapper().writeValueAsString(ratesMap);

            if (current.get(Calendar.HOUR_OF_DAY) <= 11 && current.get(Calendar.MINUTE) < 30) {
                current.add(Calendar.HOUR_OF_DAY, -24);
            }

            current.set(Calendar.HOUR_OF_DAY, UPDATE_TIME_HOURS);
            current.set(Calendar.MINUTE, UPDATE_TIME_MINUTES);
            currentRates = rateRepository.save(new Rates(0, current.getTime(), jsonRatesMap));
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public Rates getCurrentRate() {
        refreshRatesIfNeeded();
        return currentRates;
    }
}
