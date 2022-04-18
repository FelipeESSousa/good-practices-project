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
class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    void savePersonTest() {

        Person person = Person.builder()
                .name("Felipe")
                .build();

        personRepository.save(person);

        Assertions.assertThat(person.getId()).isPositive();
    }

    @Test
    @Order(2)
    void getPersonTest() {

        Person person = personRepository.findById(1L).get();

        Assertions.assertThat(person.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    void getListOfPersonsTest() {

        List<Person> persons = personRepository.findAll();

        Assertions.assertThat(persons).isNotEmpty();

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    void updatePersonTest() {

        String newName = "Jose";

        Person person = personRepository.findById(1L).get();

        person.setName(newName);

        Person personUpdated = personRepository.save(person);

        Assertions.assertThat(personUpdated.getName()).isEqualTo(newName);

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    void deletePersonTest() {

        Person person = personRepository.findById(1L).get();

        personRepository.delete(person);

        //personRepository.deleteById(1L);

        Person person1 = null;

        Optional<Person> optionalPerson = personRepository.findById(1l);

        if (optionalPerson.isPresent()) {
            person1 = optionalPerson.get();
        }

        Assertions.assertThat(person1).isNull();
    }
}


