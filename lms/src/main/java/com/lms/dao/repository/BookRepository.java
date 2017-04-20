package com.lms.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.models.Book;
import com.lms.models.Library;

/**
 * Created by bhushan on 17/4/17.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    long countByLibraryAndIsbn(Library library, String isbn);
    Book findByUuid(String uuid);
}
