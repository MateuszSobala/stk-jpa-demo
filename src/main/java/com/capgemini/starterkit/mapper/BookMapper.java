package com.capgemini.starterkit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.capgemini.starterkit.entity.Book;
import com.capgemini.starterkit.to.BookDto;

@Mapper(componentModel="spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDto);
}
