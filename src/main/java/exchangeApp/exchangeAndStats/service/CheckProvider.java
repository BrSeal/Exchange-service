package exchangeApp.exchangeAndStats.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CheckProvider {
    private static final String NO_RECORDS_YET = "No records was found!";
    private static final String TYPE_IS_NULL = "Can't convert currencies! Currency is not presented!";
    private static final String CURRENCY_NAME_FORMAT_ERROR = "Currency name must contain only 3 letters!";
    private static final String UNKNOWN_CURRENCY = "Can't find such currency!";
    private static final String AMOUNT_LESS_THAN_ONE = "You must convert one or more pieces!";

    public void ifEmptyOrNull(List list) {
        if (list == null || list.size() == 0) throw new IllegalArgumentException(NO_RECORDS_YET);
    }

    public void ifNull(Object object) {
        if (object == null) throw new IllegalArgumentException(NO_RECORDS_YET);
    }

    public void validateExchange(String from, String to, double amount, Map<String, Double> rates) {
        validateType(from, rates);
        validateType(to, rates);
        if (amount < 1) throw new IllegalArgumentException(AMOUNT_LESS_THAN_ONE);
    }

    public void validateType(String type, Map<String, Double> rates) {
        if (type == null) throw new IllegalArgumentException(TYPE_IS_NULL);
        if (type.length() != 3) throw new IllegalArgumentException(CURRENCY_NAME_FORMAT_ERROR);
        if (!rates.containsKey(type)) throw new IllegalArgumentException(UNKNOWN_CURRENCY);
    }
}
