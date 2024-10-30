package cat.udl.eps.softarch.demo.domain;

import cat.udl.eps.softarch.demo.domain.UriEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Room extends UriEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int surface;

    @ColumnDefault("false")
    private boolean isOccupied;
    private boolean hasWindow;
    private boolean hasDesk;
    private boolean hasBed;


    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    @NotNull
    private Apartment apart;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull
    @ManyToOne
    private Owner owner;

}