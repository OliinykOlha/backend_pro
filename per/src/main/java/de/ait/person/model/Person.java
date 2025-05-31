package de.ait.person.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="id")
@Entity
@Table(name="persons")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {
    @Id
    private int id;
    @Setter
    private String name;
    private LocalDate birthDate;
    @Setter
   // @Embedded
    private Address address;

    public void  updateAddress(String city, String street, Integer building) {
        if (this.address == null) {
            this.address = new Address();
        }
           this.address.setCity(city);
           this.address.setStreet(street);
           this.address.setBuilding(building);
    }


}
