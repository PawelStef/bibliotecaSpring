package com.javagda25.biblioteka.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "publishingHouse", fetch = FetchType.EAGER)
    private Set<Book> books;

   public PublishingHouse(String name) {
        this.name = name;
   }
}

