package cat.udl.eps.softarch.demo.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends User {
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String name;

    private String address;

    @Override
    @ElementCollection
    public Collection<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList("OWNER");
    }

}
