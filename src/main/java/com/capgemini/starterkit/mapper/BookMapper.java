package com.capgemini.starterkit.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.capgemini.starterkit.entity.Book;
import com.capgemini.starterkit.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.starterkit.to.BookDto;

@Mapper(componentModel="spring")
public interface BookMapper {

    BookDto bookToBookDto(Book book, @Context CycleAvoidingMappingContext context);

    Book bookDtoToBook(BookDto bookDto, @Context CycleAvoidingMappingContext context);
}
