package exchangeApp.exchange.DTO;

import exchangeApp.exchange.entity.Exchange;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeResultDTO {
    private int exchangeId;
    private double amount;

    public ExchangeResultDTO(Exchange exchange){
        exchangeId = exchange.getId();
        amount = exchange.getResultingAmount();
    }

    public ExchangeResultDTO(int exchangeId,double amount){
        this.exchangeId = exchangeId;
        this.amount = amount;
    }
}
