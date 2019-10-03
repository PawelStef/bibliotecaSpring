package com.javagda25.biblioteka.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private int yearWritten;
    @Formula(value = "(year(now()) - year_written)")
    private int howOld;
    private int numberOfPages;
    private int numberOfAvailableCopies;
//    @Formula(value = "(select count(*) from BookLent bl where bl.book_id = id and bl.dateReturned is null )")
//    private int numberOfBorrowedCopies;
    @ManyToOne(fetch = FetchType.LAZY)
    private PublishingHouse publishingHouse;

    //    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
//    private Set<Author> authors;
//
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
//    private Set<BookLent> currentLents;
    public Book(Long id, String title, int yearWritten, int numberOfPages, int numberOfAvailableCopies) {
        this.id = id;
        this.title = title;
        this.yearWritten = yearWritten;
        this.numberOfPages = numberOfPages;
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }
}


