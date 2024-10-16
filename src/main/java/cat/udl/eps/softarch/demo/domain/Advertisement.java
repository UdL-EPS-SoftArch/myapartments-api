package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
    @Min(value = 1, message = "El valor debe ser mayor que 0")
    private Double price;

    @NotNull
    private String zipCode;

    @NotNull
    private String country;

    @NotNull
    private ZonedDateTime creationDate;

    private ZonedDateTime expirationDate;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    public AdvertisementStatus adStatus;


    public Advertisement() {
        this.creationDate = ZonedDateTime.now();
    }
}
