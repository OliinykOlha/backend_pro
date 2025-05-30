package de.ait.person.service;

import de.ait.person.dto.AddressDto;
import de.ait.person.dto.CityPopulationDto;
import de.ait.person.dto.PersonDto;

public interface PersonService {
    void addPerson(PersonDto personDto);
    PersonDto getPersonById(Integer id);
    PersonDto deletePerson(Integer id);
    PersonDto updateByName(Integer id, String name);
    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);
    PersonDto[] getPersonsByCity(String city);
    PersonDto[] getPersonBetweenAge(Integer minAge, Integer maxAge);
    PersonDto[] getPersonByName(String name);
    Iterable<CityPopulationDto> getPopulation();
}
