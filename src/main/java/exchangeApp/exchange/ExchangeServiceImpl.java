package exchangeApp.exchange;

import exchangeApp.exchange.DTO.ExchangeResultDTO;
import exchangeApp.exchange.entity.Exchange;
import exchangeApp.rates.RatesService;
import exchangeApp.security.UserRepository;
import exchangeApp.security.entity.User;
import exchangeApp.stats.exchangeRating.ExchangeRatingService;
import exchangeApp.stats.exchangeRating.entity.ExchangeRating;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
    private static final String NULL_USER = "User is not presented!";

    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;
    private final ExchangeRatingService exchangeRatingService;
    private final RatesService ratesService;
    private final CheckProvider check;

    @Override
    public ExchangeResultDTO doExchange(Exchange exchange, String username) {

        Map<String, Double> rates = ratesService.getActualRates(exchange.getFrom()).getRates();
        User user=userRepository.findById(username).orElseThrow(()->new IllegalArgumentException(NULL_USER));

        check.validateExchange(exchange, rates);
        double rate = rates.get(exchange.getTo());
        double resultingAmount = exchange.getAmount() * rate;

        double amountInUsd=exchange.getAmount()*rates.get("USD");


        exchange.setRates(ratesService.getCurrentRate());
        exchange.setResultingAmount(resultingAmount);
        exchange.setAmountInUsd(amountInUsd);
        exchange.setUser(user);
        int exchangeId = exchangeRepository.save(exchange).getId();

        exchangeRatingService.add(new ExchangeRating(0,exchange.getFrom(),exchange.getTo(),1));

        return new ExchangeResultDTO(exchangeId, resultingAmount);
    }
}
