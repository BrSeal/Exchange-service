package exchangeApp.exchangeAndStats.entity;

import exchangeApp.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "operation_date", nullable = false)
    private Date operationDate;

    @Column(name = "convert_from", nullable = false)
    private String from;

    @Column(name = "convert_to", nullable = false)
    private String to;

    @Column(name = "amount", nullable = false)
    @Min(1)
    private double amount;

    @Column(name = "rate", nullable = false)
    private double rate;
}