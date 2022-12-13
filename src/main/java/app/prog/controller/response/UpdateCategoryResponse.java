package app.prog.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UpdateCategoryResponse {
    private int id;
    private String name;
}
