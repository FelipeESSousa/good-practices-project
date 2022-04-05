package br.com.goodpractices.services;

import br.com.goodpractices.domain.repository.PersonRepository;
import br.com.goodpractices.services.dto.PersonRequest;
import br.com.goodpractices.services.dto.PersonResponse;
import br.com.goodpractices.services.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonResponse save(PersonRequest person) {
        return personMapper.convert(personRepository.save(personMapper.convert(person)));
    }

    public List<PersonResponse> findAll() {
        List<PersonResponse> returnedList = personMapper.convert(personRepository.findAll());
        if (returnedList.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return returnedList;
        //return personRepository.findAll().parallelStream().map(personMapper::convert).toList();
    }

    public PersonResponse findById(Long id) {
        return personMapper.convert(personRepository.getById(id));
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
