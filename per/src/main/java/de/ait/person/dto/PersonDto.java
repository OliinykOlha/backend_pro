package de.ait.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private AddressDto address;

}

