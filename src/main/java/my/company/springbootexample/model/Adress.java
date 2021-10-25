package my.company.springbootexample.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_adress")
@Entity
public class Adress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_adress")
    private Long id_adress;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;

    @OneToOne(mappedBy = "adress")
    private User user;

    public Adress(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }
}

