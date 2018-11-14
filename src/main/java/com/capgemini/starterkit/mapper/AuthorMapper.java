package com.capgemini.starterkit.mapper;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import com.capgemini.starterkit.entity.Author;
import com.capgemini.starterkit.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.starterkit.to.AuthorDto;

@Mapper(componentModel="spring")
public interface AuthorMapper {

    AuthorDto authorToAuthorDto(Author author, @Context CycleAvoidingMappingContext context);

    List<AuthorDto> toListAuthorDto(List<Author> authors, @Context CycleAvoidingMappingContext context);

    Author authorDtoToAuthor(AuthorDto authorDto, @Context CycleAvoidingMappingContext context);
}
