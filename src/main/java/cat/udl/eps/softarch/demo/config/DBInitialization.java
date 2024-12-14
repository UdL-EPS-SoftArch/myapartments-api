package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import cat.udl.eps.softarch.demo.domain.Advertisement;
import cat.udl.eps.softarch.demo.repository.AdvertisementRepository;
import java.util.Set;

@Configuration
public class DBInitialization {
    private final OwnerRepository ownerRepository;
    private final AdvertisementStatusRepository advertisementStatusRepository;
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final ApartmentRepository apartmentRepository;
    private final AdvertisementRepository advertisementRepository;

    public DBInitialization(UserRepository userRepository, OwnerRepository ownerRepository, AdminRepository adminRepository, AdvertisementRepository advertisementRepository, ApartmentRepository apartmentRepository, AdvertisementStatusRepository advertisementStatusRepository) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
        this.apartmentRepository = apartmentRepository;
        this.adminRepository = adminRepository;
        this.advertisementRepository = advertisementRepository;
        this.advertisementStatusRepository = advertisementStatusRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        Owner mainOwner;
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
            mainOwner = new Owner();
            mainOwner.setEmail("owner@sample.app");
            mainOwner.setId("owner");
            mainOwner.setName("owner");
            mainOwner.setPhoneNumber("639826878");
            mainOwner.setPassword(defaultPassword);
            mainOwner.encodePassword();
            ownerRepository.save(mainOwner);
        }else{
            mainOwner=ownerRepository.findByName("owner").stream().findFirst().get();
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

        if(apartmentRepository.findByName("This apartment is static DO NOT TOUCH").isEmpty()){
            Apartment apartment = new Apartment();
            apartment.setDescription("This apartment is static DO NOT TOUCH");
            apartment.setName("This apartment is static DO NOT TOUCH");
            apartment.setOwner(mainOwner);
            apartment.setFloor(2);
            apartment.setAddress("This apartment is static DO NOT TOUCH");
            apartment.setPostalCode("12345");
            apartment.setCity("This apartment is static DO NOT TOUCH");
            apartment.setCountry("This apartment is static DO NOT TOUCH");
            apartment.setRegistrationDate(ZonedDateTime.now());
            apartmentRepository.save(apartment);
        }

        if(advertisementStatusRepository.findByStatus("Available").isEmpty()){
            AdvertisementStatus advertisementStatus = new AdvertisementStatus();
            advertisementStatus.setStatus("Available");
            advertisementStatusRepository.save(advertisementStatus);
        }

        if(advertisementStatusRepository.findByStatus("Reserved").isEmpty()){
            AdvertisementStatus advertisementStatus = new AdvertisementStatus();
            advertisementStatus.setStatus("Reserved");
            advertisementStatusRepository.save(advertisementStatus);
        }

        if(advertisementStatusRepository.findByStatus("Sold").isEmpty()){
            AdvertisementStatus advertisementStatus = new AdvertisementStatus();
            advertisementStatus.setStatus("Sold");
            advertisementStatusRepository.save(advertisementStatus);
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
