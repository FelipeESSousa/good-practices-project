package br.com.goodpractices.resources;

import br.com.goodpractices.services.dto.PersonRequest;
import br.com.goodpractices.services.dto.PersonResponse;
import br.com.goodpractices.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/persons/v1")
@RequiredArgsConstructor
public class PersonResource {

    private final PersonService personService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonResponse> save(@RequestBody PersonRequest person) {
        PersonResponse personCreated = personService.save(person);
        URI location = URI.create(String.format("/person/%s", personCreated.id()));
        return ResponseEntity.created(location).body(personCreated);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonResponse>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonResponse> findAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

}
