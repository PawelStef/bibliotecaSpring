package com.javagda25.biblioteka.service;

import com.javagda25.biblioteka.model.Book;
import com.javagda25.biblioteka.model.PublishingHouse;
import com.javagda25.biblioteka.repository.BookRepository;
import com.javagda25.biblioteka.repository.PublishingHouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final PublishingHouseRepository publishingHouseRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void add(Book book, Long publishingHouseId) {
        if (publishingHouseRepository.existsById(publishingHouseId)) {
            PublishingHouse ph = publishingHouseRepository.getOne(publishingHouseId);
            book.setPublishingHouse(ph);

            bookRepository.save(book);
        }else {
            throw new EntityNotFoundException("Publishing house not found.");
        }
    }

    public Optional<Book> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }

    public void remove(Book book) {
        bookRepository.delete(book);
    }
}
