package com.capgemini.starterkit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.capgemini.starterkit.entity.Author;
import com.capgemini.starterkit.to.AuthorDto;

@Mapper(componentModel="spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto authorkToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDto authorDto);
}
