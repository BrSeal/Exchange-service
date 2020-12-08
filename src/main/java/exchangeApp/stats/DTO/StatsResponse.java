package exchangeApp.stats.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class StatsResponse {
    private List<ExchangeDto> myExchanges;
    private List<String> exchangedMany;
    private List<String> moreThanAtOnes;

    public StatsResponse() {
        myExchanges = new ArrayList<>();
        exchangedMany = new ArrayList<>();
        moreThanAtOnes = new ArrayList<>();
    }
}
