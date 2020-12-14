package exchangeApp.exchange.DTO;

import exchangeApp.exchange.entity.Exchange;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Result of exchange operation")
public class ExchangeResultDTO {
    @ApiModelProperty(value = "Id of the exchange operation")
    private int exchangeId;
    @ApiModelProperty(value = "Resulting amount in final currency")
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
