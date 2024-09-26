package cat.udl.eps.softarch.demo.config;

import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BasicUserDetailsService implements UserDetailsService {

  final UserRepository userRepository;

  public BasicUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findById(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }
}
