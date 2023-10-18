package co.inventorsoft.academy.spring.restfull.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;

    private String name;
    private Integer count;
    private Double price;
    private String article_num;
    private String description;

}
