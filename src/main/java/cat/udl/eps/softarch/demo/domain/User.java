package cat.udl.eps.softarch.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Entity
@Table(name = "DemoUser") //Avoid collision with system table User
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends UriEntity<String> implements UserDetails {

	public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Id
	private String id;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Length(min = 8, max = 256)
	private String password;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean passwordReset;

	public void encodePassword() {
		this.password = passwordEncoder.encode(this.password);
	}

	@Override
	public String getUsername() { return id; }

	public void setUsername(String username) { this.id = username; }

	@Override
	@JsonValue(value = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
