package com.lms.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.lms.utils.modelutil.AbstractEntity;

/**
 * Created by bhushan on 8/4/17.
 */
@Entity
@Getter @Setter
public class Book extends AbstractEntity implements Serializable {
    public Book() {
        super();
    }
    private static final long serialVersionUID = -6906661988805072421L;
    private String isbn;
    private String name;
    private String edition;
    private String imageUrl;
    @Column(length = 2054)
    private String description;
    private Double price;
    private String publisher;
    private String authorName;
    @Column(length = 4)
    private Integer publicationYear;
    @Column(length = 8)
    private Integer numberOfAvailableCopies;
    @Column(length = 8)
    private Integer totalNumberOfCopies;
    @ManyToOne
    private Library library;
    @OneToMany
    Set<Category> categories = new HashSet<>();
}
