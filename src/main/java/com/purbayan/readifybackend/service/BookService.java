package com.purbayan.readifybackend.service;

import com.purbayan.readifybackend.dao.BookRepository;
import com.purbayan.readifybackend.dao.CheckOutRepository;
import com.purbayan.readifybackend.entity.Book;
import com.purbayan.readifybackend.entity.CheckOut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional // transactional annotation ensures that this method runs within a transaction
public class BookService {
    private BookRepository bookRepository;
    private CheckOutRepository checkOutRepository;

    // Constructor dependency injection to set up all the repository
    public BookService(BookRepository bookRepository, CheckOutRepository checkOutRepository) {
        this.bookRepository = bookRepository;
        this.checkOutRepository = checkOutRepository;
    }

    // Check if the Book has been checked out by the user or not
    public Boolean checkoutBookByUser(String userEmail, Long bookId) {
        CheckOut validateCheckout = checkOutRepository.findByUserEmailAndBookId(userEmail,bookId);
        return validateCheckout != null;
    }

    // Get the count of books loaned by the user
    public int currentLoansCount(String userEmail) {
        return checkOutRepository.findBookByUserEmail(userEmail).size();
    }

    public Book checkoutBook(String userEmail, Long bookId) throws Exception {

        // Find the book with the given bookId
        Optional<Book> book = bookRepository.findById(bookId);

        // Find the checkout object to validate if the user has already checked out the same book
        CheckOut validateCheckout = checkOutRepository.findByUserEmailAndBookId(userEmail, bookId);

        // Check if the book exists and if the user has already checked out the book or not
        // Also, check if the book has available copies to check out
        if(!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't Exception or already checked Out by User");
        }

        // Decrease the number of copies available for the book
        book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
        bookRepository.save(book.get());

        // Create a new checkout object and save it to the checkout repository
        CheckOut checkOut = new CheckOut(
                userEmail,
                LocalDate.now().toString(), // checked out date
                LocalDate.now().plusDays(7).toString(), // date the book needs to be returned
                book.get().getId()
        );

        checkOutRepository.save(checkOut);

        // Return the book that has been checked out
        return book.get();
    }


}