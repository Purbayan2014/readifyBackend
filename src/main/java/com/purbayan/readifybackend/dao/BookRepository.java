/**

 The BookRepository interface extends JpaRepository interface.
 It is responsible for performing basic CRUD operations on the Book entity.
 */
package com.purbayan.readifybackend.dao;

import com.purbayan.readifybackend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}