package exchangeApp.rates.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "The representation of current exchange rates")
public class Rates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("Id of the rate in DB")
    int id;

    @ApiModelProperty(value = "When that rate was received by server")
    @Column(name = "date")
    Date date;

    @ApiModelProperty(value = "Rates map in JSON")
    @Column(name = "rates_json")
    @Type(type = "text")
    String rates;
}
