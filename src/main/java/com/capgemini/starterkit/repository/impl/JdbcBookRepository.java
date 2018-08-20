package com.capgemini.starterkit.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.starterkit.entity.Book;
import com.capgemini.starterkit.repository.BookRepository;
import com.google.common.collect.Maps;

//@Repository
public class JdbcBookRepository implements BookRepository{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override public Book findBookByIsbn(String isbn) {
        MapSqlParameterSource isbParams = new MapSqlParameterSource("isbn", isbn);

        List<Book> books = jdbcTemplate.query("SELECT * from BOOK b where b.isbn = :isbn", isbParams, new BookResultMapper());
        if(books !=null && books.size() > 0) {
            return books.get(0);
        }
        return null;
    }

    @Override public void saveBook(Book book) {
        String sql = "INSERT INTO BOOK (id, version, title, year, isbn, active) VALUES (:id, :version, :title, :year, :isbn, :active)";

        Map<String,Object> parameters = Maps.newHashMap();
        parameters.put("id", new Random().nextLong());
        parameters.put("version", 0);
        parameters.put("title", book.getTitle());
        parameters.put("year", book.getYear());
        parameters.put("isbn", book.getIsbn());
        parameters.put("active", book.isActive());

        jdbcTemplate.update(sql,parameters);

    }

    @Override public void updateBook(Book book) {

    }

    @Override public void deleteBook(Book book) {
        String sqlDelete = "DELETE FROM book where id=:id";
        Map<String,Object> parameters = Maps.newHashMap();
        parameters.put("id", book.getId());
        jdbcTemplate.update(sqlDelete, parameters);

    }
}
