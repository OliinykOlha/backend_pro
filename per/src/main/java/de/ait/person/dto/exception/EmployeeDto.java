package de.ait.person.dto.exception;

import de.ait.person.dto.PersonDto;
import lombok.Getter;

@Getter
public class EmployeeDto extends PersonDto {
    private String company;
    private int salary;
}
