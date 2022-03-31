package br.com.goodpractices.controller;

import br.com.goodpractices.domain.model.Person;
import br.com.goodpractices.resources.PersonResource;
import br.com.goodpractices.services.dto.PersonRequest;
import br.com.goodpractices.services.dto.PersonResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String personsUrl = "/persons/v1";

    @Test
    @Order(1)
    public void save() {

        String name = "Felipe";
        PersonRequest personRequest = new PersonRequest(name);

        ResponseEntity<PersonResponse> responseEntity = this.restTemplate.postForEntity(personsUrl, personRequest, PersonResponse.class);

        PersonResponse personResponse = responseEntity.getBody();

        assertThat(personResponse).isNotNull();
        assertThat(personResponse.name()).isEqualTo(name);
    }

}
