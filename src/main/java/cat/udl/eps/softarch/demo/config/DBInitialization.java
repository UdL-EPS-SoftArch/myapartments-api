package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
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
    private final AdvertisementRepository advertisementRepository;

    public DBInitialization(UserRepository userRepository, OwnerRepository ownerRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
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
        // Advertisement
        if (!advertisementRepository.existsById(1L)) {
            Advertisement advertisement = new Advertisement();
            advertisement.setId(1L);
            advertisement.setTitle("Demo");
            advertisement.setDescription("Demo");
            advertisement.setPrice(1.0);
            advertisementRepository.save(advertisement);
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
