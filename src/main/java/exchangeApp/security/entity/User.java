package exchangeApp.security.entity;

import exchangeApp.exchange.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username")
    @Size(min = 3, max = 50)
    private String username;

    @Column(name = "password",
            nullable = false,
            length = 68)
    private String password;

    @Column(name = "enabled",
            nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Authority> authorities;

    @OneToMany(fetch= FetchType.LAZY, mappedBy = "user")
    private List<Exchange> exchanges;
}
