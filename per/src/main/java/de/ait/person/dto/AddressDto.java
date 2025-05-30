package de.ait.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String city;
    private String street;
    private Integer building;
}
