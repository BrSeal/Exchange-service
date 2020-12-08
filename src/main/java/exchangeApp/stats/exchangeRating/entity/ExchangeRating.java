package exchangeApp.stats.exchangeRating.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_currency")
    private String from;

    @Column(name = "to_currency")
    private String to;

    @Column(name = "frequency")
    private int frequency;
}
