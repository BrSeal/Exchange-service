package exchangeApp.exchangeAndStats.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchanges")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "operation_date", nullable = false)
    private Date operationDate;

    @Column(name = "convert_from", nullable = false)
    private String from;

    @Column(name = "convert_to", nullable = false)
    private String to;

    @Column(name = "amount_to_exchange", nullable = false)
    @Min(1)
    private double amount;

    @Column(name = "resulting_amount", nullable = false)
    private double resultingAmount;

    @ManyToOne
    @JoinColumn(name = "rates_id", nullable = false)
    private Rate rates;
}