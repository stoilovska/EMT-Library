package mk.ukim.finki.emt.lab1.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;

@Data
public class BookDto {
    private String name;

//    private Country category;

    private Long author;

    private Integer  availableCopies;

    private Category category;

    public BookDto() {
    }

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
