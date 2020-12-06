package exchangeApp.rates.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalApiResponse {
    private String disclaimer;
    private String licence;
    private long timestamp;
    private String base;
    private Map<String, Double> rates = new HashMap<>();
}
