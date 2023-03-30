package mk.ukim.finki.emt.lab1.web.controller;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;
import mk.ukim.finki.emt.lab1.service.AuthorService;
import mk.ukim.finki.emt.lab1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "books"})
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;


    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getBookPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/add-form-book")
    public String addBookPage(Model model) {
        List<Author> authors = this.authorService.findAll();
        List<Category> categories = Arrays.stream(Category.values()).toList();

        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);


        model.addAttribute("bodyContent", "add-book");
        return "master-template";
    }

    @GetMapping("/categories")
    public String getCategoriesPage(Model model) {
//        List<Author> authors = this.authorService.findAll();

        List<Category> categories = Arrays.stream(Category.values()).toList();

        model.addAttribute("categories", categories);

        model.addAttribute("bodyContent", "categories");
        return "master-template";
    }

    @GetMapping("/edit-form-book/{id}")
    public String editBookPage(@PathVariable Long id, Model model) {
        if (this.bookService.findById(id).isPresent()) {
            Book book = this.bookService.findById(id).get();
            List<Author> authors = this.authorService.findAll();
            List<Category> categories = Arrays.stream(Category.values()).toList();

            model.addAttribute("categories", categories);
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            model.addAttribute("bodyContent", "add-book");
            return "master-template";
        }

        return "redirect:/authors?error=BookNotFound";
    }

    @GetMapping("/markAsTaken/{id}")
    public String MarkAsTaken(@PathVariable Long id, Model model) {
        this.bookService.markAsTaken(id);
        List<Book> books = this.bookService.findAll();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam(required = false) Long id,
                           @RequestParam String name,
                           @RequestParam Long author,
                           @RequestParam String category,
                           @RequestParam Integer availableCopies) {

        if (id != null) {
            this.bookService.edit(id, name, author, category, availableCopies);
        } else {
            this.bookService.save(name, author, category, availableCopies);
        }

        return "redirect:/books";
    }

}
