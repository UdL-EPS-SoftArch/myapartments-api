package cat.udl.eps.softarch.demo.domain;

import cat.udl.eps.softarch.demo.domain.UriEntity;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

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
    private boolean isOccupied;
    private boolean hasWindow;
    private boolean hasDesk;
    private boolean hasBed;



    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true) // Only serialize the pet ID
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Apartment apartment;

}