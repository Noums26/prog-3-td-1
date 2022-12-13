package app.prog.controller.mapper;

import app.prog.controller.response.AuthorResponse;
import app.prog.controller.response.CreateAuthorResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.UpdateAuthorResponse;
import app.prog.model.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorRestMapper {
    public AuthorResponse toRest(AuthorEntity author) {
        return AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .particularity(author.getParticularity())
                .hasParticularity(author.hasParticularity())
                .build();
    }

    public AuthorEntity toDomain(CreateAuthorResponse rest) {
        return AuthorEntity.builder()
                .name(rest.getName())
                .particularity(rest.getParticularity())
                .build();
    }

    public AuthorEntity toDomain(UpdateAuthorResponse rest) {
        return AuthorEntity.builder()
                .id(rest.getId())
                .name(rest.getName())
                .particularity(rest.getParticularity())
                .build();
    }
}
