package mk.ukim.finki.emt.lab1.web.controller;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String getCountryPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Country> countries = this.countryService.findAll();
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "countries");
        return "master-template";
    }

    @GetMapping("/add-form-countries")
    public String addBookPage(Model model) {

        model.addAttribute("bodyContent", "add-countries");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam String continent) {


            this.countryService.save(name, continent);

        return "redirect:/countries";
    }

}
