/**

 The BookRepository interface extends JpaRepository interface.
 It is responsible for performing basic CRUD operations on the Book entity.
 */
package com.purbayan.readifybackend.dao;

import com.purbayan.readifybackend.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;

public interface BookRepository extends JpaRepository<Book, Long> {

//    search by book title
    Page<Book> findByTitleContaining(@RequestParam("title") String title, Pageable pageable);
//    search by book category
    Page<Book> findByCategory(@RequestParam("category") String category, Pageable pageable);

}