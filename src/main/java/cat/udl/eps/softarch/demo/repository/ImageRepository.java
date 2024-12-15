package cat.udl.eps.softarch.demo.repository;


import cat.udl.eps.softarch.demo.domain.Apartment;
import cat.udl.eps.softarch.demo.domain.Image;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Long>, PagingAndSortingRepository<Image, Long> {
    @NotNull
    Optional<Image> findById(@NotNull @Param("id") Long id);
    List<Image> findByApartment(@Param("country") Apartment Apartment);
    List<Image> findByFilename(@Param("filename") String filename);
    List<Image> findByContent(@Param("content") String content);
}
