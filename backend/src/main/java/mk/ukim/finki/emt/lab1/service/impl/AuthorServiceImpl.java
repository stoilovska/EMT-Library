package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Author;
import mk.ukim.finki.emt.lab1.model.Book;
import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.dto.AuthorDto;
import mk.ukim.finki.emt.lab1.repository.jpa.AuthorRepository;
import mk.ukim.finki.emt.lab1.repository.jpa.CountryRepository;
import mk.ukim.finki.emt.lab1.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long country) {
        Country country1 = this.countryRepository.findById(country).orElseThrow();
        Author author = new Author(name,surname,country1);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long country) {
        Author author = this.authorRepository.findById(id).orElseThrow();
        Country country1 = this.countryRepository.findById(country).orElseThrow();

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country1);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow();
        Country country = this.countryRepository.findById(authorDto.getCountry()).orElseThrow();

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
