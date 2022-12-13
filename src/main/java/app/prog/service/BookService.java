package app.prog.service;

import app.prog.exception.NotFoundException;
import app.prog.model.BookEntity;
import app.prog.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    public List<BookEntity> createBooks(List<BookEntity> toCreate) {
        return repository.saveAll(toCreate);
    }

    public List<BookEntity> updateBooks(List<BookEntity> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    //TODO-3: should I use Integer here or int ? Why ?
    public ResponseEntity<BookEntity> deleteBook(int bookEntityId) {
        /*
        TIPS: From the API, the Class Optional<T> is :
        A container object which may or may not contain a non-null value.
        If a value is present, isPresent() returns true.
        If no value is present, the object is considered empty and isPresent() returns false.

        T is the type of the value, for example : here the class type is BookEntity
         */
        Optional<BookEntity> optional = repository.findById(bookEntityId);
        if (optional.isPresent()) {
            repository.delete(optional.get());
            // return new ResponseEntity(optional.get(), HttpStatus.OK);
            return ResponseEntity.ok(optional.get());
        } else {
        /*
        TODO-5 : The exception appears as an internal server error, status 500.
        We all know that the appropriate error status is the 404 Not Found.
        Any solution to do this ?
        These links may help you :
        Link 1 : https://www.baeldung.com/spring-response-entity
        Link 2 : https://www.baeldung.com/exception-handling-for-rest-with-spring
         */
            // throw new ResponseEntityExceptionHandler("BookEntity." + bookEntityId + " not found");
            throw new NotFoundException("BookEntity." + bookEntityId + " not found");
        }
    }
}
