package de.ait.person.service;

import de.ait.person.dto.ChildDto;
import de.ait.person.dto.PersonDto;
import de.ait.person.dto.exception.EmployeeDto;
import de.ait.person.model.Child;
import de.ait.person.model.Employee;
import de.ait.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonModelDtoMapper {

    private final ModelMapper modelMapper;

    public PersonDto toDto(Person person) {
        if (person instanceof Child) {
            return modelMapper.map(person, ChildDto.class);

        }
        if (person instanceof Employee) {
            return modelMapper.map(person, EmployeeDto.class);

        }
        return modelMapper.map(person, PersonDto.class);
    }

    public Person fromDto(PersonDto personDto) {
        if (personDto instanceof ChildDto) {
            return modelMapper.map(personDto, Child.class);

        }
        if (personDto instanceof EmployeeDto) {
            return modelMapper.map(personDto, Employee.class);

        }
        return modelMapper.map(personDto, Person.class);
    }
}
