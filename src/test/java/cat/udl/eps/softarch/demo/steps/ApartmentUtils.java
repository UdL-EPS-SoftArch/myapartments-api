package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Owner;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;
import cat.udl.eps.softarch.demo.repository.OwnerRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    public static Apartment buildApartment(String name, String floor, String address, String PostalCode, String city, String country, String description, String date, Owner owner) {
        int parsedFloor = Integer.parseInt(floor);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;  // Ajusta el formato si es necesario
        ZonedDateTime parsedDate = ZonedDateTime.parse(date, formatter);

        return Apartment.builder()
                .name(name)
                .floor(parsedFloor)
                .address(address)
                .postalCode(PostalCode)
                .city(city)
                .country(country)
                .description(description)
                .registrationDate(parsedDate)
                .owner(owner)
                .build();
    }

}

