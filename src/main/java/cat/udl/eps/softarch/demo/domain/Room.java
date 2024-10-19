package cat.udl.eps.softarch.demo.domain;

import cat.udl.eps.softarch.demo.domain.UriEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private Long id;
    private int surface;
    @ColumnDefault("false")
    private boolean isOccupied;
    private boolean hasWindow;
    private boolean hasDesk;
    private boolean hasBed;


    @ManyToOne
    private Apartment apart;

    @ManyToOne
    private Owner owner;

}