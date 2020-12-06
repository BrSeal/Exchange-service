package exchangeApp.stats.DTO;

import exchangeApp.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExchangeDto {
    private int id;
    private String user;
    private Date operationDate;
    private String from;
    private String to;
    private double amount;
    private double resultingAmount;

    public ExchangeDto(Exchange exchange){
        id=exchange.getId();
        user=exchange.getUser().getUsername();
        operationDate=exchange.getOperationDate();
        from=exchange.getFrom();
        to=exchange.getTo();
        amount=exchange.getAmount();
        resultingAmount=exchange.getResultingAmount();
    }
}
