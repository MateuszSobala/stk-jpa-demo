package com.capgemini.starterkit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.capgemini.starterkit.entity.Library;
import com.capgemini.starterkit.to.LibraryDto;

@Mapper(componentModel="spring")
public interface LibraryMapper {

    LibraryMapper INSTANCE = Mappers.getMapper(LibraryMapper.class);

    LibraryDto libraryToLibraryDto(Library library);

    Library libraryDtoToLibrary(LibraryDto libraryDto);

}
