package app.prog.controller.mapper;

import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CategoryResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.CreateCategoryResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.controller.response.UpdateCategoryResponse;
import app.prog.model.BookEntity;
import app.prog.model.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryRestMapper {
    public CategoryResponse toRest(CategoryEntity domain) {
        return CategoryResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }

    public CategoryEntity toDomain(CreateCategoryResponse rest) {
        return CategoryEntity.builder()
                .name(rest.getName())
                .build();
    }

    public CategoryEntity toDomain(UpdateCategoryResponse rest) {
        return CategoryEntity.builder()
                .id(rest.getId())
                .name(rest.getName())
                .build();
    }
}
