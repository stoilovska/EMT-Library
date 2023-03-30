package mk.ukim.finki.emt.lab1.web.rest;

import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/categories")
public class CategoryRestController {

    @GetMapping
    public List<Category> findAll() {
        return Arrays.stream(Category.values()).toList();
    }


}
