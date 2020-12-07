package exchangeApp.stats.DTO;

import exchangeApp.stats.exchangeRating.entity.ExchangeRating;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRatingDto {
  private int frequency;
  private String from;
  private String to;

  public ExchangeRatingDto(ExchangeRating rating){
      frequency=rating.getFrequency();
      from=rating.getFrom();
      to=rating.getTo();
  }
}
