package cat.udl.eps.softarch.demo.steps;

import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.repository.ApartmentRepository;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ApartmentUtils {


    public static Apartment buildApartment(String name, String floor, String address, String PostalCode, String city, String country, String description, String date) {
        int parsedFloor = Integer.parseInt(floor);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;  // Ajusta el formato si es necesario
        ZonedDateTime parsedDate = ZonedDateTime.parse(date, formatter);

        Apartment apartment_new = new Apartment();
        apartment_new.setName(name);
        apartment_new.setFloor(parsedFloor);
        apartment_new.setAddress(address);
        apartment_new.setPostalCode(PostalCode);
        apartment_new.setCity(city);
        apartment_new.setCountry(country);
        apartment_new.setDescription(description);
        apartment_new.setRegistrationDate(parsedDate);

        return apartment_new;
    }

}

