package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "apartment")
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

    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Room> rooms;

    @OneToOne
    @PrimaryKeyJoinColumn
    private ApartmentDetails detail;
}
