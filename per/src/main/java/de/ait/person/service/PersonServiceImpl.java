package de.ait.person.service;

import de.ait.person.dao.PersonRepository;
import de.ait.person.dto.AddressDto;
import de.ait.person.dto.CityPopulationDto;
import de.ait.person.dto.PersonDto;
import de.ait.person.dto.exception.ConflictException;
import de.ait.person.dto.exception.NotFoundException;
import de.ait.person.model.Person;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;



@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void addPerson(PersonDto personDto) {
        if(personRepository.existsById(personDto.getId())){
            throw new ConflictException("Person with " + personDto.getId() + " already exists.");
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
    }

    @Override
    public PersonDto getPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional
    @Override
    public PersonDto deletePerson(Integer id) {
        Person person = personRepository.getPersonById(id);
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional
    @Override
    public PersonDto updateByName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(NotFoundException::new);
        person.setName(name);
        return modelMapper.map(person, PersonDto.class);
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
        return modelMapper.map(person, PersonDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] getPersonsByCity(String city) {
       return personRepository.findByAddressCityIgnoreCase(city)
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] getPersonBetweenAge(Integer minAge, Integer maxAge) {
        LocalDate from = LocalDate.now().minusYears(minAge);
        LocalDate to = LocalDate.now().minusYears(maxAge);
        return personRepository.findByBirthDateBetween(from, to)
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] getPersonByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(person -> modelMapper.map(person, PersonDto.class))
                .toArray(PersonDto[]::new);
    }

    @Override
    public Iterable<CityPopulationDto> getPopulation() {
        return personRepository.getCitiesPopulation();
    }
}
