package br.com.goodpractices.services.mapper;

import br.com.goodpractices.domain.model.Person;
import br.com.goodpractices.services.dto.PersonRequest;
import br.com.goodpractices.services.dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    PersonResponse convert(Person person);

    @Mapping(target = "id", ignore = true)
    Person convert(PersonRequest sourceCode);

    List<PersonResponse> convert(List<Person> persons);

}
