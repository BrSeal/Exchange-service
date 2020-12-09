package exchangeApp.stats.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsRequest {
    private double moreThanInTotal;
    private double moreThanAtOnes;
}
