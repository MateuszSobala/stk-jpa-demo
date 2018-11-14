package com.capgemini.starterkit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.starterkit.datatype.BookType;
import com.capgemini.starterkit.entity.Book;
import com.capgemini.starterkit.mapper.AuthorMapper;
import com.capgemini.starterkit.mapper.common.CycleAvoidingMappingContext;
import com.capgemini.starterkit.repository.AuthorRepository;
import com.capgemini.starterkit.to.AuthorDto;
import com.capgemini.starterkit.to.BookDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    public void shouldAddBookWithAuthors() {
        // given
        BookDto odaDoJpa = new BookDto();
        odaDoJpa.setActive(true);
        odaDoJpa.setIsbn("12345");
        odaDoJpa.setTitle("Oda do JPA");
        odaDoJpa.setType(BookType.HORROR);
        odaDoJpa.setYear(2018);

        AuthorDto adamMickiewicz = new AuthorDto();
        adamMickiewicz.setFirstName("Adam");
        adamMickiewicz.setLastName("Mickiewicz");
        odaDoJpa.getAuthor().add(adamMickiewicz);
        adamMickiewicz.getBook().add(odaDoJpa);

        AuthorDto henrykSienkiewicz = new AuthorDto();
        henrykSienkiewicz.setFirstName("Henryk");
        henrykSienkiewicz.setLastName("Sienkiewicz");
        odaDoJpa.getAuthor().add(henrykSienkiewicz);
        henrykSienkiewicz.getBook().add(odaDoJpa);

        // when
        BookDto newlyAddedOdaDoJpa =  bookService.add(odaDoJpa); // cascade persist
        // both authors can be added to book before add(odaDoJpa), because then Hibernate cascades the persist
        // operation hence creating the authors as well.

        // then
        assertNotNull(newlyAddedOdaDoJpa);
        assertEquals("12345", newlyAddedOdaDoJpa.getIsbn());
        assertEquals(2, newlyAddedOdaDoJpa.getAuthor().size());

    }

    @Test
    public void shouldAddBookToExistingAuthors() {
        // given
        BookDto odaDoJpa = new BookDto();
        odaDoJpa.setActive(true);
        odaDoJpa.setIsbn("12345");
        odaDoJpa.setTitle("Oda do JPA");
        odaDoJpa.setType(BookType.HORROR);
        odaDoJpa.setYear(2018);

        AuthorDto adamMickiewicz = new AuthorDto();
        adamMickiewicz.setFirstName("Adam");
        adamMickiewicz.setLastName("Mickiewicz");
        odaDoJpa.getAuthor().add(adamMickiewicz);
        adamMickiewicz.getBook().add(odaDoJpa);

        AuthorDto henrykSienkiewicz = new AuthorDto();
        henrykSienkiewicz.setFirstName("Henryk");
        henrykSienkiewicz.setLastName("Sienkiewicz");
        odaDoJpa.getAuthor().add(henrykSienkiewicz);
        henrykSienkiewicz.getBook().add(odaDoJpa);

        bookService.add(odaDoJpa);

        BookDto easyJpa = new BookDto();
        easyJpa.setActive(true);
        easyJpa.setIsbn("00000");
        easyJpa.setTitle("Easy JPA");
        easyJpa.setType(BookType.ADVENTURE);
        easyJpa.setYear(2018);

        // when
        BookDto newlyAddedEasyJpa = bookService.add(easyJpa); // cascade persist
        // Sienkiewicz couldn't be added to book before add(easyJpa), because then Hibernate would cascade the
        // persist operation and hence would try to persist an existing entity.

        AuthorDto existingHenrykSienkiewicz = authorMapper.authorToAuthorDto(
            authorRepository.findByLastName("Sienkiewicz").get(0), CycleAvoidingMappingContext.create());
        newlyAddedEasyJpa.getAuthor().add(existingHenrykSienkiewicz);
        existingHenrykSienkiewicz.getBook().add(newlyAddedEasyJpa);

        AuthorDto czeslawMilosz = new AuthorDto();
        czeslawMilosz.setFirstName("Czeslaw");
        czeslawMilosz.setLastName("Milosz");
        newlyAddedEasyJpa.getAuthor().add(czeslawMilosz);
        czeslawMilosz.getBook().add(newlyAddedEasyJpa);

        BookDto newlyUpdatedEasyJpa = bookService.update(newlyAddedEasyJpa); // cascade merge
        // Sienkiewicz should be added later to an existing book on which one calls an update where merge
        // operation is cascaded.

        // then
        assertNotNull(newlyUpdatedEasyJpa);
        assertEquals("00000", newlyUpdatedEasyJpa.getIsbn());
        assertEquals(2, newlyUpdatedEasyJpa.getAuthor().size());
        assertEquals(3, authorRepository.findAll().size());
    }

    @Test
    public void shouldDeleteBook() {
        // given
        BookDto odaDoJpa = new BookDto();
        odaDoJpa.setActive(true);
        odaDoJpa.setIsbn("12345");
        odaDoJpa.setTitle("Oda do JPA");
        odaDoJpa.setType(BookType.HORROR);
        odaDoJpa.setYear(2018);

        AuthorDto adamMickiewicz = new AuthorDto();
        adamMickiewicz.setFirstName("Adam");
        adamMickiewicz.setLastName("Mickiewicz");
        odaDoJpa.getAuthor().add(adamMickiewicz);
        adamMickiewicz.getBook().add(odaDoJpa);

        AuthorDto henrykSienkiewicz = new AuthorDto();
        henrykSienkiewicz.setFirstName("Henryk");
        henrykSienkiewicz.setLastName("Sienkiewicz");
        odaDoJpa.getAuthor().add(henrykSienkiewicz);
        henrykSienkiewicz.getBook().add(odaDoJpa);

        bookService.add(odaDoJpa);

        BookDto easyJpa = new BookDto();
        easyJpa.setActive(true);
        easyJpa.setIsbn("00000");
        easyJpa.setTitle("Easy JPA");
        easyJpa.setType(BookType.ADVENTURE);
        easyJpa.setYear(2018);

        BookDto newlyAddedEasyJpa = bookService.add(easyJpa);

        AuthorDto existingHenrykSienkiewicz = authorMapper.authorToAuthorDto(
            authorRepository.findByLastName("Sienkiewicz").get(0), CycleAvoidingMappingContext.create());
        newlyAddedEasyJpa.getAuthor().add(existingHenrykSienkiewicz);
        existingHenrykSienkiewicz.getBook().add(newlyAddedEasyJpa);

        AuthorDto czeslawMilosz = new AuthorDto();
        czeslawMilosz.setFirstName("Czeslaw");
        czeslawMilosz.setLastName("Milosz");
        newlyAddedEasyJpa.getAuthor().add(czeslawMilosz);
        czeslawMilosz.getBook().add(newlyAddedEasyJpa);

        BookDto newlyUpdatedEasyJpa = bookService.update(newlyAddedEasyJpa);

        // when
        bookService.delete(newlyUpdatedEasyJpa); // no cascade

        // then
        Book bookByIsbn = bookService.findBookByIsbn("00000");
        assertNull(bookByIsbn);
        assertEquals(3, authorRepository.findAll().size());
    }
}
