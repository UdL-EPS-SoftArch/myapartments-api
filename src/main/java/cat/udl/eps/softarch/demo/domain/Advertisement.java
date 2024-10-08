package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
public class Advertisement extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private String zipCode;

    @NotNull
    private String country;

    private ZonedDateTime creationDate;

    @NotNull
    private String city;

    @NotNull
    private String address;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Visit> visits;

    public Advertisement() {
        this.creationDate = ZonedDateTime.now();
    }
}
