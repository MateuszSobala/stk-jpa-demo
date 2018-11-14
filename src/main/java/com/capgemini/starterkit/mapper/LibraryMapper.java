package com.capgemini.starterkit.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.capgemini.starterkit.entity.Library;
import com.capgemini.starterkit.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.starterkit.to.LibraryDto;

@Mapper(componentModel="spring")
public interface LibraryMapper {

    LibraryDto libraryToLibraryDto(Library library, @Context CycleAvoidingMappingContext context);

    Library libraryDtoToLibrary(LibraryDto libraryDto, @Context CycleAvoidingMappingContext context);

}
