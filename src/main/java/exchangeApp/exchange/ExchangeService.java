package exchangeApp.exchange;

import exchangeApp.exchange.DTO.ExchangeResultDTO;
import exchangeApp.exchange.entity.Exchange;

public interface ExchangeService {
    ExchangeResultDTO doExchange(Exchange exchange,String username);
}
