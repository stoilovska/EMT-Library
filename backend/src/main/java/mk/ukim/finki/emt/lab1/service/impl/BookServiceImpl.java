package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.dto.BookDto;
import mk.ukim.finki.emt.lab1.model.enumerations.Category;
import mk.ukim.finki.emt.lab1.model.exceptions.BookWasTakenException;
import mk.ukim.finki.emt.lab1.repository.jpa.AuthorRepository;
import mk.ukim.finki.emt.lab1.repository.jpa.BookRepository;
import mk.ukim.finki.emt.lab1.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, Long author, String category ,Integer availableCopies) {
        Author author1 = this.authorRepository.findById(author).orElseThrow();
        Category category1 = Category.valueOf(category);
        Book book = new Book(name,category1,author1,availableCopies);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Long author, String category, Integer availableCopies) {
        Author author1 = this.authorRepository.findById(author).orElseThrow();
        Book book = this.bookRepository.findById(id).orElseThrow();

        book.setName(name);
        book.setAuthor(author1);
        book.setAvailableCopies(availableCopies);
        book.setCategory(Category.valueOf(category));

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow();
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow();
        Category category = bookDto.getCategory();

        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(category);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow();

        if (book.getAvailableCopies()>0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            book.setCategory(book.getCategory());
            book.setAuthor(book.getAuthor());
            book.setName(book.getName());
        } else {
            throw new BookWasTakenException(id);
        }
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
