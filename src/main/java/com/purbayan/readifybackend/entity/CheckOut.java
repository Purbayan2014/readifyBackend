package com.purbayan.readifybackend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * The Checkout entity represents a book in the system.
 *
 * It is marked as a JPA entity, meaning that it maps to a table in a database.
 */

@Entity
@Table(name = "checkout")
@Data
public class CheckOut {
    public CheckOut() {}

    public CheckOut(String userEmail, String checkOutDate, String returnDate, long bookId) {
        this.userEmail = userEmail;
        this.checkOutDate = checkOutDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
    }

    /**
     * The unique identifier for this book review.
     *
     * It is marked as the primary key for the database table and is automatically
     * generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    /**
     * The user email of the book reviewer.
     *
     */
    @Column(name = "user_email")
    private String userEmail;


    /**
     * The checkout Date when the book was reviewed and checked out
     */
    @Column(name = "checkout_date")
    private String checkOutDate;

    /**
     * The return Date of the book review
     */
    @Column(name = "return_date")
    private String returnDate;

    /**
     *  The id of the book for which the review is left
     */
    @Column(name = "book_id")
    private Long bookId;
}
