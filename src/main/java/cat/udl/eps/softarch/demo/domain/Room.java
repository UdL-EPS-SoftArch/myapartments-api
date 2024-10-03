package cat.udl.eps.softarch.demo.domain;

import cat.udl.eps.softarch.demo.domain.UriEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

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
    private boolean isOccupied;
    private boolean hasWindow;
    private boolean hasDesk;
    private boolean hasBed;

}