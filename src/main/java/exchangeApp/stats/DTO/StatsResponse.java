package exchangeApp.stats.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsResponse {
    private List<ExchangeDto> myExchanges;
    private List<String> exchangedMany;
    private List<String> moreThanAtOnes;
}
