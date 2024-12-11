package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.ZonedDateTime;


@EqualsAndHashCode(callSuper = true)
@Entity(name = "visit")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visit extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "visit_date")
    private ZonedDateTime when;

    @ManyToOne
    @JoinColumn(name = "username_id")
    private User username;

    // Setter
    // Getter
    @Setter
    @Getter
    @NotNull
    private Status status = Status.PENDING;

    public enum Status {
        PENDING("pending"),
        ACCEPTED("accepted"),
        REJECTED("rejected");

        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    @ManyToOne
    @NotNull
    @JsonIdentityReference(alwaysAsId = true)
    private Advertisement advertisement;

    public void setVisitDateTime(ZonedDateTime parse) {
        this.when = parse;
    }
}

