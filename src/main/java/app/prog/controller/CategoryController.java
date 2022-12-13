package app.prog.controller;

import app.prog.controller.mapper.BookRestMapper;
import app.prog.controller.mapper.CategoryRestMapper;
import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CategoryResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.CreateCategoryResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.controller.response.UpdateCategoryResponse;
import app.prog.model.BookEntity;
import app.prog.model.CategoryEntity;
import app.prog.service.BookService;
import app.prog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final CategoryRestMapper mapper;

    @GetMapping("/books")
    public List<CategoryResponse> getCategories() {
        return service.getCategories().stream()
                .map(mapper::toRest)
                .toList();
    }

    @PostMapping("/books")
    public List<CategoryResponse> createCategory(@RequestBody List<CreateCategoryResponse> toCreate) {
        List<CategoryEntity> domain = toCreate.stream()
                .map(mapper::toDomain)
                .toList();
        return service.createCategory(domain).stream()
                .map(mapper::toRest)
                .toList();
    }

    @PutMapping("/books")
    public List<CategoryResponse> updateCategory(@RequestBody List<UpdateCategoryResponse> toUpdate) {
        List<CategoryEntity> domain = toUpdate.stream()
                .map(mapper::toDomain)
                .toList();
        return service.updateCategory(domain).stream()
                .map(mapper::toRest)
                .toList();
    }

    @DeleteMapping("/books/{bookId}")
    public CategoryResponse deleteCategory(@PathVariable Integer categoryId) {
        return mapper.toRest(service.deleteCategory(categoryId).getBody());
    }
}
