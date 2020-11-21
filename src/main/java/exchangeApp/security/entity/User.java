package exchangeApp.security.entity;

import exchangeApp.exchangeAndStats.entity.Exchange;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Exchange> exchanges;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Authority> authorities;
}
