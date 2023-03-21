package mk.ukim.finki.emt.emtlab.service.impl;

import mk.ukim.finki.emt.emtlab.models.Author;
import mk.ukim.finki.emt.emtlab.models.Country;
import mk.ukim.finki.emt.emtlab.models.dto.AuthorDto;
import mk.ukim.finki.emt.emtlab.models.exception.InvalidCountryIdException;
import mk.ukim.finki.emt.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emt.emtlab.repository.CountryRepository;
import mk.ukim.finki.emt.emtlab.service.AuthorService;
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
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> addNewAuthor(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(Long.valueOf(authorDto.getCountry()))
                .orElseThrow(InvalidCountryIdException::new);
        Author author = new Author(authorDto.getName(),authorDto.getSurname(),country);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
