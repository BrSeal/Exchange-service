package exchangeApp.exchangeAndStats.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exchangeApp.MyExceptionHandler;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.entity.ExternalApiResponse;
import exchangeApp.exchangeAndStats.entity.Rate;
import exchangeApp.exchangeAndStats.repository.ExchangeRepository;
import exchangeApp.exchangeAndStats.repository.RateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
@Transactional
@Log4j2
public class ExchangeServiceImpl implements ExchangeService {

    private final static String STANDARD_TYPE = "USD";
    private static final int DIGITS_AFTER_COMMA = 6;

    @Value("${externalServerErr}")
    private String EXTERNAL_SERVER_ERR;

    @Value("${ratesUpdateTime.HOURS}")
    private int UPDATE_TIME_HOURS;

    @Value("${ratesUpdateTime.MINUTES}")
    private int UPDATE_TIME_MINUTES;


    @Value("${getRatesUrl}")
    private String GET_RATES_URL;

    private final ExchangeRepository exchangeRepository;
    private final RateRepository rateRepository;
    private final CheckProvider check;

    private Map<String, Double> ratesMap;
    private Rate currentRate;

    public ExchangeServiceImpl(ExchangeRepository exchangeRepository, RateRepository rateRepository, CheckProvider check) {
        this.exchangeRepository = exchangeRepository;
        this.rateRepository = rateRepository;
        this.check = check;
    }

    @Override
    public Map<String, Double> getActualRates() {
       return getActualRates(STANDARD_TYPE);
    }

    @Override
    public Map<String, Double> getActualRates(@NotNull String base) {
        refreshRates();
        if (!base.equals(STANDARD_TYPE)) {
            check.validateType(base, ratesMap);
            double rateToUsd = ratesMap.get(base);
            ratesMap.forEach((k, v) -> ratesMap.put(k, ceil(v / rateToUsd)));
        }
        return ratesMap;
    }


    @Override
    public ExchangeResultDTO doExchange(Exchange exchange) {

        Map<String, Double> rates = getActualRates(exchange.getFrom());

        check.validateExchange(exchange, rates);

        double rate = rates.get(exchange.getTo());

        double resultingAmount = exchange.getAmount() * rate;

        exchange.setRates(currentRate);
        int exchangeId = exchangeRepository.save(exchange).getId();

        return new ExchangeResultDTO(exchangeId, resultingAmount);
    }

    private void refreshRates() {

        if (ratesMap == null) requestRatesFromExternalAPI();

        else {
            Calendar current = Calendar.getInstance();
            Calendar fromRate = new GregorianCalendar();
            fromRate.setTime(currentRate.getDate());
            fromRate.add(Calendar.HOUR, 24);

            if(current.compareTo(fromRate)>0) requestRatesFromExternalAPI();
        }
    }

    private void requestRatesFromExternalAPI() {
        try {
            ratesMap = new RestTemplate().getForObject(GET_RATES_URL, ExternalApiResponse.class)
                    .getRates();

            Calendar current = Calendar.getInstance();
            String jsonRatesMap = new ObjectMapper().writeValueAsString(ratesMap);

            if(current.get(Calendar.HOUR_OF_DAY)<=11&&current.get(Calendar.MINUTE)<30){
                current.add(Calendar.HOUR_OF_DAY,-24);
            }

            current.set(Calendar.HOUR_OF_DAY, UPDATE_TIME_HOURS);
            current.set(Calendar.MINUTE, UPDATE_TIME_MINUTES);
            currentRate = rateRepository.save(new Rate(0, current.getTime(), jsonRatesMap));
        } catch (JsonProcessingException ex) {
            log.error(MyExceptionHandler.beautifyStackTrace(ex));
            throw new IllegalStateException(ex);
        }
    }

    private double ceil(double d) {
        long multiplier = 10 ^ DIGITS_AFTER_COMMA;
        return Math.ceil(d * multiplier) / multiplier;
    }
}
