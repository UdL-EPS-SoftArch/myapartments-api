package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

public class Review extends UriEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @Min(value = 0)
    @Max(value = 5)
    private Double rating;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @ManyToOne
    public Advertisement advertisement;
}
