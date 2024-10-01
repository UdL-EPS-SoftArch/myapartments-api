package cat.udl.eps.softarch.demo.domain;

import jakarta.validation.constraints.NotNull;

public class Advertisment {
    
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Double price;
}
