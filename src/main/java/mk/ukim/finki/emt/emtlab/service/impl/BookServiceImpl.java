package mk.ukim.finki.emt.emtlab.service.impl;

import mk.ukim.finki.emt.emtlab.models.Book;
import mk.ukim.finki.emt.emtlab.models.dto.BookDto;
import mk.ukim.finki.emt.emtlab.models.enumeration.Category;
import mk.ukim.finki.emt.emtlab.models.exception.InvalidAuthorIdException;
import mk.ukim.finki.emt.emtlab.models.exception.InvalidBookIdException;
import mk.ukim.finki.emt.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emt.emtlab.repository.BookRepository;
import mk.ukim.finki.emt.emtlab.service.BookService;
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
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> addNewBook(BookDto bookDto) {
        Book book = new Book(bookDto.getName(), Category.valueOf(String.valueOf(bookDto.getCategory())),
                this.authorRepository.findById(bookDto.getAuthor().getId()).orElseThrow(InvalidAuthorIdException::new),bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> editBook(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setCategory(Category.valueOf(String.valueOf(bookDto.getCategory())));
        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public boolean markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        if(book.getAvailableCopies()==0){
            return false;
        }
        book.setAvailableCopies((book.getAvailableCopies()));
        this.bookRepository.save(book);
        return true;
    }
}
