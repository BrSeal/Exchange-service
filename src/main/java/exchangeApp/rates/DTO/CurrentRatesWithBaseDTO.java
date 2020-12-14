package exchangeApp.rates.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@ApiModel(description = "Exchange rates and the base")
public class CurrentRatesWithBaseDTO {
    @ApiModelProperty(value = "3 letter base currency shortcut", example = "USD")
    private String base;
    @ApiModelProperty(value = "Map of {currency name}:{amount} values, representing price of one base currency in other currency")
    private Map<String,Double> rates;
}
