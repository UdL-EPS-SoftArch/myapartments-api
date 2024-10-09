package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class ApartmentDetails extends UriEntity<Long>{
    @Id
    private Long id;
    private float square;
    private int numBathrooms;
    private int numBedrooms;
    private boolean hasAC;
    private boolean hasElevator;

}
