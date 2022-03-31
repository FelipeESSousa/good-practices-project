package br.com.goodpractices.repository;

import br.com.goodpractices.domain.model.Person;
import br.com.goodpractices.domain.repository.PersonRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePersonTest() {

        Person person = Person.builder()
                .name("Felipe")
                .build();

        personRepository.save(person);

        Assertions.assertThat(person.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getEmployeeTest() {

        Person person = personRepository.findById(1L).get();

        Assertions.assertThat(person.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest() {

        List<Person> persons = personRepository.findAll();

        Assertions.assertThat(persons.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeeTest() {

        String newName = "Jose";

        Person person = personRepository.findById(1L).get();

        person.setName(newName);

        Person personUpdated = personRepository.save(person);

        Assertions.assertThat(personUpdated.getName()).isEqualTo(newName);

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest() {

        Person person = personRepository.findById(1L).get();

        personRepository.delete(person);

        //employeeRepository.deleteById(1L);

        Person person1 = null;

        Optional<Person> optionalEmployee = personRepository.findById(1l);

        if (optionalEmployee.isPresent()) {
            person1 = optionalEmployee.get();
        }

        Assertions.assertThat(person1).isNull();
    }
}


