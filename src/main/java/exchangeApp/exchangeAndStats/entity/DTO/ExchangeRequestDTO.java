package exchangeApp.exchangeAndStats.entity.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.security.entity.User;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExchangeRequestDTO {
    private int userId;
    private double amount;
    private String from;
    private String to;

    public Exchange toExchange() {
        User user = new User();
        user.setId(userId);
        return new Exchange(0, user, new Date(), from, to, amount,0d);
    }
}
