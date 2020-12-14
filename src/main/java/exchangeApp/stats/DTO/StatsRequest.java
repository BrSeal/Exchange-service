package exchangeApp.stats.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "This object defines which users will be shown in the result")
public class StatsRequest {
    @ApiModelProperty(value = "Summary amount of exchanges of the user is greater than that value (in USD equivalent)")
    private double moreThanInTotal;
    @ApiModelProperty(value = "User that exchanged more than that value in one transaction (in USD equivalent)")
    private double moreThanAtOnes;
}
