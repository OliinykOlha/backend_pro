package de.ait.person.dao;

import de.ait.person.dto.CityPopulationDto;
import de.ait.person.model.Child;
import de.ait.person.model.Employee;
import de.ait.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person getPersonById(Integer id);
//    @Query("select p from Person p where p.name=?1")
    Stream<Person> findByNameIgnoreCase(String name);
//    @Query("select p from Person p where p.address.city=?1")
    Stream<Person> findByAddressCityIgnoreCase(String  city);
    Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

    @Query("select new de.ait.person.dto.CityPopulationDto(p.address.city, count (p)) from Person p " +
            "group by p.address.city order by count (p) desc")
    List<CityPopulationDto> getCitiesPopulation();

   Child[] getChildrenBy();
   Employee[] getEmployeesBySalaryBetween(int min, int max);

}
