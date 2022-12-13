package app.prog.service;

import app.prog.exception.NotFoundException;
import app.prog.model.BookEntity;
import app.prog.model.CategoryEntity;
import app.prog.repository.BookRepository;
import app.prog.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<CategoryEntity> getCategories() {
        return repository.findAll();
    }

    public List<CategoryEntity> createCategory(List<CategoryEntity> toCreate) {
        return repository.saveAll(toCreate);
    }

    public List<CategoryEntity> updateCategory(List<CategoryEntity> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    //TODO-3: should I use Integer here or int ? Why ?
    public ResponseEntity<CategoryEntity> deleteCategory(int categoryEntityId) {
        /*
        TIPS: From the API, the Class Optional<T> is :
        A container object which may or may not contain a non-null value.
        If a value is present, isPresent() returns true.
        If no value is present, the object is considered empty and isPresent() returns false.

        T is the type of the value, for example : here the class type is BookEntity
         */
        Optional<CategoryEntity> optional = repository.findById(categoryEntityId);
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
            throw new NotFoundException("CategoryEntity." + categoryEntityId + " not found");
        }
    }
}
