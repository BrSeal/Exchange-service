package exchangeApp.exchangeAndStats.entity.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import exchangeApp.exchangeAndStats.entity.Exchange;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExchangeRequestDTO {
    private String username;
    private double amount;
    private String from;
    private String to;

    public Exchange toExchange() {
        return new Exchange( username,new Date(), from, to, amount);
    }
}
