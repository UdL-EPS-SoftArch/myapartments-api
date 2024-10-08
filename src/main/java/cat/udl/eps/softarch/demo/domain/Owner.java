package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends User {
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String name;

    private String address;
}
