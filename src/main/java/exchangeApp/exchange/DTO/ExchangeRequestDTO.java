package exchangeApp.exchange.DTO;

import exchangeApp.exchange.entity.Exchange;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExchangeRequestDTO {
    private double amount;
    private String from;
    private String to;

    public Exchange toExchange() {
        return new Exchange( null,new Date(), from, to, amount);
    }
}
