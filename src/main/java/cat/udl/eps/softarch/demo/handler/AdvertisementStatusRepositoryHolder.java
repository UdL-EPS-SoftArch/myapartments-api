package cat.udl.eps.softarch.demo.handler;

import cat.udl.eps.softarch.demo.repository.AdvertisementStatusRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementStatusRepositoryHolder {

    @Getter
    private static AdvertisementStatusRepository repository;

    @Autowired
    public AdvertisementStatusRepositoryHolder(AdvertisementStatusRepository advertisementStatusRepository) {
        repository = advertisementStatusRepository;
    }

}
