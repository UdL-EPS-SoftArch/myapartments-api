package cat.udl.eps.softarch.demo.domain;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Room {
    Integer id;
    Integer surface;
    boolean isOccupied;
    boolean hasWindow;
    boolean hasDesk;
    boolean hasBed;
}
