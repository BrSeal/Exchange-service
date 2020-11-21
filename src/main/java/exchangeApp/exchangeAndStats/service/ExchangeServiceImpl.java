package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeRequestDTO;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.entity.ExchangeRate;
import exchangeApp.exchangeAndStats.repository.ExchangeRepository;
import exchangeApp.security.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Value("${getRatesUrl}")
    private String GET_RATES_URL;

    private String STANDARD_TYPE="USD";

    private final ExchangeRepository repository;
    private final CheckProvider check;

    public ExchangeServiceImpl(ExchangeRepository repository, CheckProvider check) {
        this.repository = repository;
        this.check = check;
    }

    @Override
    public Map<String, Double> requestRatesFromExternalAPI(String type) {
        if(type==null) type=STANDARD_TYPE;

        RestTemplate template= new RestTemplate();
        Map<String, Double> rates =template.getForObject(GET_RATES_URL, ExchangeRate.class)
                .getRates();

        if(!type.equals(STANDARD_TYPE)) {
            check.validateType(type, rates);
            double rateToUsd = rates.get(type);
            rates.forEach((k, v) -> rates.put(k, v / rateToUsd));
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
        Exchange exchange = repository.findById(id).get();
        check.ifNull(exchange);
        return exchange;
    }

    @Override
    @Transactional
    public ExchangeResultDTO doExchange(ExchangeRequestDTO dto, User user) {
        String from = dto.getFrom().toUpperCase();
        String to = dto.getTo().toUpperCase();
        double amount = dto.getAmount();
        Map<String, Double> rates = requestRatesFromExternalAPI(from);

        check.validateExchange(from, to, amount, rates);

        double rate=rates.get(to);

        double resultingAmount = amount * rate;

        Exchange result = new Exchange(0, user, new Date(), from, to, amount, rate);
        int exchangeId = repository.save(result).getId();
        return new ExchangeResultDTO(exchangeId, resultingAmount);
    }
}
