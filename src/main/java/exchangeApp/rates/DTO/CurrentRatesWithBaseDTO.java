package exchangeApp.rates.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CurrentRatesWithBaseDTO {
    private String base;
    private Map<String,Double> rates;
}
