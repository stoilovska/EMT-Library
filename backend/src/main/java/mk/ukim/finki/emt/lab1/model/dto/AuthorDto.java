package mk.ukim.finki.emt.lab1.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.lab1.model.Country;

import javax.persistence.ManyToOne;

@Data
public class AuthorDto {
    private String name;

    private String surname;

    private Long country;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
