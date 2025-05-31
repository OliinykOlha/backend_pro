package de.ait.person.service;

import de.ait.person.dao.PersonRepository;
import de.ait.person.dto.AddressDto;
import de.ait.person.dto.ChildDto;
import de.ait.person.dto.CityPopulationDto;
import de.ait.person.dto.PersonDto;
import de.ait.person.dto.exception.ConflictException;
import de.ait.person.dto.exception.EmployeeDto;
import de.ait.person.dto.exception.NotFoundException;
import de.ait.person.model.Address;
import de.ait.person.model.Child;
import de.ait.person.model.Employee;
import de.ait.person.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
    private final PersonRepository personRepository;
    private final PersonModelDtoMapper mapper;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            throw new ConflictException("Person with " + personDto.getId() + " already exists.");
        }
        personRepository.save(mapper.fromDto(personDto));
    }

    @Override
    public PersonDto getPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapper.toDto(person);
    }

    @Transactional
    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.getPersonById(id);
        return mapper.toDto(person);
    }

    @Transactional
    @Override
    public PersonDto updateByName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        person.setName(name);
        return mapper.toDto(person);
    }

    @Transactional
    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        String city = addressDto.getCity();
        String street = addressDto.getStreet();
        int building = addressDto.getBuilding();
        person.updateAddress(city, street, building);
        personRepository.save(person);
        return mapper.toDto(person);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] getPersonsByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .map(person -> mapper.toDto(person))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] getPersonBetweenAge(Integer minAge, Integer maxAge) {
        LocalDate from = LocalDate.now().minusYears(minAge);
        LocalDate to = LocalDate.now().minusYears(maxAge);
        return personRepository.findByBirthDateBetween(from, to)
                .map(mapper::toDto)
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] getPersonByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(mapper::toDto)
                .toArray(PersonDto[]::new);
    }

    @Override
    public Iterable<CityPopulationDto> getPopulation() {
        return personRepository.getCitiesPopulation();
    }

    @Override
    public EmployeeDto[] findEmployeesBySalary(int min, int max) {
         Employee[] employees = personRepository.getEmployeesBySalaryBetween(min, max);
        return modelMapper.map(employees, EmployeeDto[].class);
    }

    @Override
    public ChildDto[] findAllChildren() {
        Child[] children = personRepository.getChildrenBy();
        return modelMapper.map(children, ChildDto[].class);
    }


    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            Person person = new Person(1000, "Jack", LocalDate.of(1980, 05, 11),
                    new Address("Berlin", "Sorbenstrasse", 23));
            Child child = new Child(2000, "Jane", LocalDate.of(2021, 04, 10),
                    new Address("Menden", "Berlinerstrasse", 23), "Kita");
            Employee employee = new Employee(3000, "Mary", LocalDate.of(1992, 12, 1),
                    new Address("Hamburg", "Rosenweg", 34), "Google", 80000);
            personRepository.save(person);
            personRepository.save(child);
            personRepository.save(employee);
        }
    }
}
