package my.company.springbootexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "role")
    private User user;

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }


    @Override
    public String getAuthority() {
        return role;
    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
