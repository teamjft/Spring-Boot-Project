package com.lms.dao.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lms.models.Book;
import com.lms.models.Library;

/**
 * Created by bhushan on 17/4/17.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    long countByLibraryAndIsbn(Library library, String isbn);
    Book findByUuid(String uuid);
    List<Book> findByLibraryAndIsbnIn(Library library, Set<String> books);
    Page<Book> findByLibrary(Library library, Pageable pageable);
}
