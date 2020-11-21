package exchangeApp.exchangeAndStats.entity.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import exchangeApp.exchangeAndStats.entity.Exchange;

@Data
@NoArgsConstructor
public class ExchangeResultDTO {
    private int exchangeId;
    private double amount;

    public ExchangeResultDTO(Exchange exchange){
        exchangeId = exchange.getId();
        amount = exchange.getAmount()*exchange.getRate();
    }

    public ExchangeResultDTO(int exchangeId,double amount){
        this.exchangeId = exchangeId;
        this.amount = amount;
    }
}
