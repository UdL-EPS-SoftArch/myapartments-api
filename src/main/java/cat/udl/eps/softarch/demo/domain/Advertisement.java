package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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


    public Advertisement() {
        this.creationDate = ZonedDateTime.now();
    }
}
