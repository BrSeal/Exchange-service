package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.entity.ExchangeRating;
import exchangeApp.exchangeAndStats.repository.ExchangeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final ExchangeRatingService exchangeRatingService;
    private final RatesService ratesService;
    private final CheckProvider check;

    public ExchangeServiceImpl(ExchangeRepository exchangeRepository, ExchangeRatingService exchangeRatingService, RatesService ratesService, CheckProvider check) {
        this.exchangeRepository = exchangeRepository;
        this.exchangeRatingService = exchangeRatingService;
        this.ratesService = ratesService;
        this.check = check;
    }

    @Override
    public Exchange getById(int id) {
        Exchange exchange = exchangeRepository.findById(id).orElseThrow();
        check.ifNull(exchange);
        return exchange;
    }

    @Override
    public List<Exchange> getAll() {
        List<Exchange> result = (List<Exchange>) exchangeRepository.findAll();
        check.ifEmptyOrNull(result);
        return result;
    }

    @Override
    public List<Exchange> findAllByUsername(String username) {
        return exchangeRepository.findAllByUsername(username);
    }

    @Override
    public ExchangeResultDTO doExchange(Exchange exchange) {

        Map<String, Double> rates = ratesService.getActualRates(exchange.getFrom());

        check.validateExchange(exchange, rates);
        double rate = rates.get(exchange.getTo());
        double resultingAmount = exchange.getAmount() * rate;

        exchange.setRates(ratesService.getCurrentRate());
        exchange.setResultingAmount(resultingAmount);
        int exchangeId = exchangeRepository.save(exchange).getId();

        exchangeRatingService.add(new ExchangeRating(0,exchange.getFrom(),exchange.getTo(),1));

        return new ExchangeResultDTO(exchangeId, resultingAmount);
    }
}
