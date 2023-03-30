package mk.ukim.finki.emt.lab1.web.controller;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.service.AuthorService;
import mk.ukim.finki.emt.lab1.service.CountryService;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public String getAuthorPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = this.authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        this.authorService.deleteById(id);
        return "redirect:/authors";
    }

    @GetMapping("/add-form-author")
     public String addAuthorPage(Model model) {
        List<Country> countries = this.countryService.findAll();

        model.addAttribute("countries", countries);

        model.addAttribute("bodyContent", "add-author");
        return "master-template";
    }

    @GetMapping("/edit-form-author/{id}")
    public String editAuthorPage(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id).isPresent()) {
            Author author = this.authorService.findById(id).get();
            List<Country> countries = this.countryService.findAll();

            model.addAttribute("countries", countries);
            model.addAttribute("author", author);
            model.addAttribute("bodyContent", "add-author");
            return "master-template";
        }

        return "redirect:/authors?error=AuthorNotFound";
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam(required = false) Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam Long countries) {

        if (id != null){
            this.authorService.edit(id,name,surname,countries);
        } else {
            this.authorService.save(name,surname,countries);
        }

        return "redirect:/authors";
    }

}
