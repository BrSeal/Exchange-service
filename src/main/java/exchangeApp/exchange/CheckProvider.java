package exchangeApp.exchange;

import exchangeApp.exchange.entity.Exchange;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CheckProvider {
    private static final String TYPE_IS_NULL = "Can't convert currencies! Currency is not presented!";
    private static final String CURRENCY_NAME_FORMAT_ERROR = "Currency name must contain only 3 letters!";
    private static final String UNKNOWN_CURRENCY = "Can't find such currency!";
    private static final String AMOUNT_LESS_THAN_ONE = "You must convert one or more pieces!";
    private static final String SAME_BASES = "You must convert one currency to another!";


    public void validateExchange(Exchange exchange, Map<String, Double> rates) {
        validateCurrencyType(exchange.getFrom(), rates);
        validateCurrencyType(exchange.getTo(), rates);
        if(exchange.getFrom().equals(exchange.getTo())) throw new IllegalArgumentException(SAME_BASES);
        if (exchange.getAmount() < 1) throw new IllegalArgumentException(AMOUNT_LESS_THAN_ONE);
    }

    public void validateCurrencyType(String type, Map<String, Double> rates) {
        if (type == null) throw new IllegalArgumentException(TYPE_IS_NULL);
        if (type.length() != 3) throw new IllegalArgumentException(CURRENCY_NAME_FORMAT_ERROR);
        if (!rates.containsKey(type)) throw new IllegalArgumentException(UNKNOWN_CURRENCY);
    }
}
