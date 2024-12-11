package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApartmentDetails extends UriEntity<Long> {
    @Id
    private Long id;
    private float square;
    @NotNull
    @Min(1)
    private int numBathrooms;
    @NotNull
    @Min(1)
    private int numBedrooms;
    private boolean hasAC;
    private boolean hasElevator;
}