package com.purbayan.readifybackend.dao;

import com.purbayan.readifybackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// Define an interface named BookRepository that extends JpaRepository.
// JpaRepository is a Spring Data interface for generic CRUD operations
// on a repository of a specific type.
public interface BookRepository extends JpaRepository<Book, Long> {
}


