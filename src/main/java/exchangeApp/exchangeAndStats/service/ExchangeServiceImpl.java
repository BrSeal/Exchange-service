package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.entity.ExternalApiResponse;
import exchangeApp.exchangeAndStats.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    private final static String STANDARD_TYPE = "USD";

    @Value("${externalServerErr}")
    private String EXTERNAL_SERVER_ERR;


    @Value("${getRatesUrl}")
    private String GET_RATES_URL;



    private final ExchangeRepository repository;
    private final CheckProvider check;

    public ExchangeServiceImpl(ExchangeRepository repository, CheckProvider check) {
        this.repository = repository;
        this.check = check;
    }

    @Override
    public Map<String, Double> requestRatesFromExternalAPI(String base) {
        if (base == null) base = STANDARD_TYPE;

        Map<String, Double> rates = new RestTemplate().getForObject(GET_RATES_URL, ExternalApiResponse.class)
                .getRates();

        check.ifNull(rates, EXTERNAL_SERVER_ERR);

        if (!base.equals(STANDARD_TYPE)) {
            check.validateType(base, rates);
            double rateToUsd = rates.get(base);
            rates.forEach((k, v) -> rates.put(k, Math.ceil(v / rateToUsd * 1_000_000) / 1_000_000));
        }
        return rates;
    }

    @Override
    public List<Exchange> getAll() {
        List<Exchange> result = (List<Exchange>) repository.findAll();
        check.ifEmptyOrNull(result);
        return result;
    }

    @Override
    public List<Exchange> findAllByUserId(int id) {
        List<Exchange> result = repository.findAllByUserId(id);
        check.ifEmptyOrNull(result);
        return result;
    }

    @Override
    public Exchange getById(int id) {
        Exchange exchange = repository.findById(id).orElseThrow();
        check.ifNull(exchange);
        return exchange;
    }

    @Override
    public ExchangeResultDTO doExchange(Exchange exchange) {
        Map<String, Double> rates = requestRatesFromExternalAPI(exchange.getFrom());

        check.validateExchange(exchange, rates);

        double rate = rates.get(exchange.getTo());

        double resultingAmount = exchange.getAmount() * rate;

        int exchangeId = repository.save(exchange).getId();

        return new ExchangeResultDTO(exchangeId, resultingAmount);
    }
}
