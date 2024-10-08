package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Visit extends UriEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private ZonedDateTime when;

    @ManyToOne(fetch = FetchType.LAZY)  // Many visits to one advertisement
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;
}
