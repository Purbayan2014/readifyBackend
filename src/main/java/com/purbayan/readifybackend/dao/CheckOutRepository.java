package com.purbayan.readifybackend.dao;

import com.purbayan.readifybackend.entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {

    CheckOut findByUserEmailAndBookId(String userEmail, Long bookId);
}
