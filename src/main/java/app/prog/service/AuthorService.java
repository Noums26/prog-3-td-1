package app.prog.service;

import app.prog.controller.mapper.AuthorRestMapper;
import app.prog.controller.response.AuthorResponse;
import app.prog.exception.NotFoundException;
import app.prog.model.AuthorEntity;
import app.prog.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorRestMapper mapper;

    public List<AuthorEntity> getAuthors() {
        return repository.findAll();
    }

    public AuthorEntity getByName(String name) {
        return repository.findByName(name);
    }

    public List<AuthorEntity> createAuthor(List<AuthorEntity> toCreate) {
        return repository.saveAll(toCreate);
    }

    public List<AuthorEntity> updateAuthor(List<AuthorEntity> toUpdate) {
        return repository.saveAll(toUpdate);
    }

    public ResponseEntity<AuthorEntity> deleteAuthor(int authorEntityId) {
        Optional<AuthorEntity> optional = repository.findById(authorEntityId);
        if (optional.isPresent()) {
            repository.delete(optional.get());
            return ResponseEntity
                    .status(200)
                    .body(optional.get());
        } else {
            throw new NotFoundException("AuthorEntity." + authorEntityId + " not found");
        }
    }
}
