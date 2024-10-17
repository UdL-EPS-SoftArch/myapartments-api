package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;

import java.util.List;
import java.util.Optional;

public class ApartmentUtils {
    public static Apartment getRoomByTitle(ApartmentRepository apartmentRepository, String apartmentName) {
        List<Apartment> apartmentList = apartmentRepository.findByName(apartmentName);
        Optional<Apartment> apartment = null;
        if (!apartmentList.isEmpty()) {
            apartment = apartmentList.stream().findFirst();
        }
        if (apartment == null) {
            return null;
        } else {
            return apartment.get();
        }
    }
}
