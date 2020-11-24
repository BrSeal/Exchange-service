package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.repository.ExchangeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StatsServiceImpl implements StatsService {

    private final ExchangeRepository exchangeRepository;
    private final ExchangeRatingService exchangeRatingService;
    private final CheckProvider check;


   // private final String CURRENCY_RATINGS_QUERY = "SELECT convert_from, convert_to, COUNT(*) AS occurency FROM exchanges GROUP BY convert_from, convert_to ORDER BY occurency DESC";

    public StatsServiceImpl(ExchangeRepository exchangeRepository, ExchangeRatingService exchangeRatingService, CheckProvider check) {
        this.exchangeRepository = exchangeRepository;
        this.exchangeRatingService = exchangeRatingService;
        this.check = check;
    }

    @Override
    public List<Exchange> getAll() {
        List<Exchange> result = (List<Exchange>) exchangeRepository.findAll();
        check.ifEmptyOrNull(result);
        return result;
    }

    @Override
    public List<Exchange> findAllByUsername(String username) {
        List<Exchange> result = exchangeRepository.findAllByUsername(username);
        check.ifEmptyOrNull(result);
        return result;
    }

    @Override
    public List<String> getUsersChangedMoreThan(double amount, String base) {
        return null;
    }

    @Override
    public List<String> getUsersTotalExchangedMoreThan(double amount, String base) {
        return null;
    }

    @Override
    public List<String> getExchangeRating() {

        return null;
    }

    @Override
    public Exchange getById(int id) {
        Exchange exchange = exchangeRepository.findById(id).orElseThrow();
        check.ifNull(exchange);
        return exchange;
    }

}
