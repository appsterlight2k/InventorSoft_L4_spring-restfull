package co.inventorsoft.academy.spring.restfull.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;

    @NotBlank
    @Size(min = 5, max = 2000)
    private String name;

    @NotNull
    @Min(0)
    private Integer count;

    @NotNull
    @Min(0)
    private Double price;

    @NotNull
    private String article_num;

    private String description;

}
