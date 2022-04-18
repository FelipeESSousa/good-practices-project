package br.com.goodpractices.controller;

import br.com.goodpractices.services.dto.PersonRequest;
import br.com.goodpractices.services.dto.PersonResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String personsUrl = "/persons/v1";

    private final String felipeName = "Felipe";

    private final String joseName = "Jose";

    @Test
    @Order(0)
    void searchForNoExistingObject() {
        getByIdExpectNoContent(1l);
    }

    @Test
    @Order(0)
    void findAllNoExistingObject() {
        ResponseEntity<PersonResponse[]> responseEntity = this.restTemplate.getForEntity(personsUrl, PersonResponse[].class);
        PersonResponse[] personResponse = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(personResponse).isNull();
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    void save() {
        postForName(felipeName);
        postForName(joseName);
    }

    @Test
    @Order(2)
    void findById() {
        getByIdSuccess(1l);
    }

    @Test
    @Order(3)
    void findAll() {
        ResponseEntity<PersonResponse[]> responseEntity = this.restTemplate.getForEntity(personsUrl, PersonResponse[].class);
        PersonResponse[] personResponse = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personResponse).isNotNull();
        assertThat(personResponse[0].name()).isEqualTo(felipeName);
        assertThat(personResponse[1].name()).isEqualTo(joseName);
    }

    @Test
    @Order(4)
    void deleteById() {
        Long id = 1l;
        this.restTemplate.delete(personsUrl + "/" + id);
        this.getByIdExpectNoContent(id);
    }

    @Test
    @Order(5)
    void deleteAll() {
        Long idFelipe = 1l;
        Long idJose = 2L;
        this.restTemplate.delete(personsUrl);
        this.getByIdExpectNoContent(idFelipe);
        this.getByIdExpectNoContent(idJose);
    }

    private void postForName(String name) {
        PersonRequest personRequest = new PersonRequest(name);

        ResponseEntity<PersonResponse> responseEntity = this.restTemplate.postForEntity(personsUrl, personRequest, PersonResponse.class);
        PersonResponse personResponse = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(personResponse).isNotNull();
        assertThat(personResponse.name()).isEqualTo(name);
    }

    private void getByIdSuccess(Long id) {
        ResponseEntity<PersonResponse> responseEntity = this.restTemplate.getForEntity(personsUrl + "/" + id, PersonResponse.class);

        PersonResponse personResponse = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(personResponse).isNotNull();
    }

    private void getByIdExpectNoContent(Long id) {
        ResponseEntity<PersonResponse> responseEntity = this.restTemplate.getForEntity(personsUrl + "/" + id, PersonResponse.class);

        PersonResponse personResponse = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(personResponse).isNull();
    }
}
