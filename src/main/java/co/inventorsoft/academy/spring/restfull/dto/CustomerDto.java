package co.inventorsoft.academy.spring.restfull.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

}
