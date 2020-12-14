package exchangeApp.stats.DTO;

import exchangeApp.stats.exchangeRating.entity.ExchangeRating;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "Holds info about how many times users exchanged from one currency to another")
public class ExchangeRatingDto {
    @ApiModelProperty(value = "How many times that type of exchange was done")
  private int frequency;
    @ApiModelProperty(value = "From what currency exchange was done")
  private String from;
    @ApiModelProperty(value = "To what currency exchange was done")
  private String to;

  public ExchangeRatingDto(ExchangeRating rating){
      frequency=rating.getFrequency();
      from=rating.getFrom();
      to=rating.getTo();
  }
}
