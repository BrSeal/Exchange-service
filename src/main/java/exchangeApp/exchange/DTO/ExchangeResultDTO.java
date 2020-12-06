package exchangeApp.exchange.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import exchangeApp.exchange.entity.Exchange;

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