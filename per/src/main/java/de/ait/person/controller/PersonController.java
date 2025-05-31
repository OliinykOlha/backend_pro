package de.ait.person.controller;


import de.ait.person.dto.AddressDto;
import de.ait.person.dto.ChildDto;
import de.ait.person.dto.CityPopulationDto;
import de.ait.person.dto.PersonDto;
import de.ait.person.dto.exception.EmployeeDto;
import de.ait.person.service.PersonService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestBody PersonDto personDto) {
       personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }

    @DeleteMapping("/id")
    public PersonDto deletePerson(@PathVariable  Integer id) {
        return personService.deletePerson(id);
    }

    @PatchMapping("/{id}/name/{name}")
    public PersonDto updateByName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updateByName(id, name);
    }

    @PatchMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return personService.updatePersonAddress(id, addressDto);
    }

    @GetMapping("city/{city}")
    public PersonDto[] getPersonsByCity(@PathVariable String city) {
        return personService.getPersonsByCity(city);
    }

    @GetMapping("/ages/{minAge}/{maxAge}")
    public PersonDto[] getPersonBetweenAge(@PathVariable Integer minAge, Integer maxAge) {
        return personService.getPersonBetweenAge(minAge, maxAge);
    }

    @GetMapping("/name/{name}")
    public PersonDto[] getPersonByName(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getPopulation() {

        return personService.getPopulation();
    }

    @GetMapping("/salary/{min}/{max}")
    public EmployeeDto[] findEmployeesBySalary(@PathVariable Integer min, @PathVariable Integer max) {
        return personService.findEmployeesBySalary(min, max);
    }

    @GetMapping("/children")
   public ChildDto[] findAllChildren() {
      return personService.findAllChildren();
   }
}
