package br.com.goodpratices.services.mapper;

import br.com.goodpratices.domain.model.Person;
import br.com.goodpratices.services.dto.PersonRequest;
import br.com.goodpratices.services.dto.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    PersonResponse convert(Person person);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "id", target = "id")
    Person convert(PersonResponse sourceCode);

    Person convert(PersonRequest sourceCode);

    List<PersonResponse> convert(List<Person> persons);

}
