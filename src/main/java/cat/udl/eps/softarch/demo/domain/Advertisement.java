package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Setter
@Getter
@Entity
public class Advertisement extends UriEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String country;

    @NotBlank
    private String address;

    @NotNull
    private ZonedDateTime creationDate;

    private ZonedDateTime expirationDate;


    @NotNull
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    public AdvertisementStatus adStatus;

    @NotNull
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    public Apartment apartment;


    public Advertisement() {
        this.creationDate = ZonedDateTime.now();
    }
}
