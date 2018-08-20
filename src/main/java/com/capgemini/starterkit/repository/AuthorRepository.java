package com.capgemini.starterkit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.starterkit.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByLastName(String lastName);
}
