package exchangeApp.exchangeAndStats.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class CurrentRatesWithBaseDTO {
    private String base;
    private Map<String,Double> rates;
}
