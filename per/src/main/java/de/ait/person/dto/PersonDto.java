package de.ait.person.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.ait.person.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ChildDto.class, name = "child"),
        @JsonSubTypes.Type(value = Employee.class, name = "employee"),
        @JsonSubTypes.Type(value = PersonDto.class, name = "person")
})
public class PersonDto {
    private Integer id;
    private String name;
    private LocalDate birthDate;
    private AddressDto address;

}

