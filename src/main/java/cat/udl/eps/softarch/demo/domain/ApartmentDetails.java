package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class ApartmentDetails extends UriEntity<Long> {
    @Id
    private Long id;
    private float square;
    @NonNull
    @Min(1)
    private int numBathrooms;
    @NonNull
    @Min(1)
    private int numBedrooms;
    private boolean hasAC;
    private boolean hasElevator;

}
