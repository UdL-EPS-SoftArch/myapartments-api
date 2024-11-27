package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.util.Arrays;
import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import java.util.Set;

@Configuration
public class DBInitialization {
    private final OwnerRepository ownerRepository;
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final AdvertisementRepository advertisementRepository;

    public DBInitialization(UserRepository userRepository, OwnerRepository ownerRepository, AdminRepository adminRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
        this.adminRepository = adminRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default user
        if (!userRepository.existsById("demo")) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setId("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }
        // Default admin
        if (!adminRepository.existsById("admin")) {
            Admin user = new Admin();
            user.setEmail("admin@sample.app");
            user.setId("admin");
            user.setPassword(defaultPassword);
            user.encodePassword();
            adminRepository.save(user);
        }

        if (!ownerRepository.existsById("owner")) {
            Owner owner = new Owner();
            owner.setEmail("owner@sample.app");
            owner.setId("owner");
            owner.setName("Abde");
            owner.setPhoneNumber("639826878");
            owner.setPassword(defaultPassword);
            owner.encodePassword();
            ownerRepository.save(owner);
        }

        if (!ownerRepository.existsById("owner1")) {
            Owner owner = new Owner();
            owner.setEmail("owner1@sample.app");
            owner.setId("owner1");
            owner.setName("Ibra");
            owner.setPhoneNumber("600000000");
            owner.setPassword(defaultPassword);
            owner.encodePassword();
            ownerRepository.save(owner);
        }
        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setId("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }


        }
    }
}
