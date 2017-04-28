package com.lms.utils.beans;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotEmpty;

import com.lms.models.Book;


/**
 * Created by bhushan on 17/4/17.
 */
@Setter @Getter
public class BookBean {
    private Long id;

    @Size(max = 255)
    @NotEmpty
    private String isbn;

    @Size(max = 255)
    @NotEmpty
    private String name;

    @Size(max = 255)
    private String edition;

    @Size(max = 2044)
    private String description;

    @Digits(integer = 6, fraction = 6)
    private Double price;

    @Size(max = 255)
    private String publisher;

    @Size(max = 255)
    private String authorName;

    private String imageUrl;
    private String uuid;

    private Integer publicationYear;

    private Integer numberOfAvailableCopies;

    @NotNull
    private Integer totalNumberOfCopies;

    private Set<CategoryBean> categoryBeens = new HashSet<>();

    public static Book convertBean(BookBean bookBean) {
        Book book =  new Book();
        book.setTotalNumberOfCopies(bookBean.getTotalNumberOfCopies());
        book.setNumberOfAvailableCopies(bookBean.getNumberOfAvailableCopies());
        book.setName(bookBean.getName());
        book.setDescription(bookBean.getDescription());
        book.setAuthorName(bookBean.getAuthorName());
        book.setEdition(bookBean.getEdition());
        book.setIsbn(bookBean.getIsbn());
        book.setPrice(bookBean.getPrice());
        book.setPublisher(bookBean.getPublisher());
        book.setPublicationYear(bookBean.getPublicationYear());
        book.setImageUrl(bookBean.getImageUrl());
        return book;
    }
}
