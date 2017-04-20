package com.lms.services.book;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lms.models.Book;
import com.lms.models.Library;

/**
 * Created by bhushan on 9/4/17.
 */
public interface BookService {
    Book get(Long id);
    List<Book> getAll();
    Book create(Book book);
    void delete(Long id);
    void update(Book books);
    Page<Book> getPageRequest(Integer pageNumber);
    Long countBYLibraryAndIsbn(Library library, String isbn);
    Book findByUuid(String uuid);
}
