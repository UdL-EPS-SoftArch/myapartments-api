package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Apartment extends UriEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private int floor;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private String description;
    private ZonedDateTime registrationDate;


    @OneToMany(mappedBy = "apartament", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Room> rooms;
}

