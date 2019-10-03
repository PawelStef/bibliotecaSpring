package com.javagda25.biblioteka.controller;

import com.javagda25.biblioteka.model.Book;
import com.javagda25.biblioteka.model.PublishingHouse;
import com.javagda25.biblioteka.service.BookService;
import com.javagda25.biblioteka.service.PublishingHouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/book/")
public class BookController {
    private final BookService bookService;
    private final PublishingHouseService publishingHouseService;

    @GetMapping(path = "/list")
    public String list (Model model){
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("booklist", bookList);
        return "book-list";
    }

    @GetMapping(path = "/add")
    public String add (Model model, Book book){
        model.addAttribute("book", book);
        model.addAttribute("publishingHouses", publishingHouseService.getAllHouse());
        return "book-add";
    }

    @PostMapping(path = "/add")
    public String add (Book book, Long publishingHouseId){
        bookService.add(book, publishingHouseId);
        return "redirect:/book/list";
    }

    @GetMapping(path = "/edit/{edit_book_id}")
    public String edit(Model model,
                       @PathVariable(name = "edit_book_id") Long bookId){
        Optional<Book> bookOptional = bookService.findById(bookId);
        if(bookOptional.isPresent()){
            model.addAttribute("book",bookOptional.get());
            return "book-add";
        }
        return "redirect:/book/list";
    }

    @GetMapping(path = "/remove/{remove_book_id}")
    public String remove(Model model,
                         @PathVariable(name = "remove_book_id") Long bookId){
        Optional<Book> bookOptional = bookService.findById(bookId);
        if(bookOptional.isPresent()){
            bookService.remove(bookOptional.get());
        }
        return "redirect:/book/list";
    }

    @GetMapping("/details/{id}")
    public String details(Model model,
                          HttpServletRequest request,
                          @PathVariable(name = "id") Long id) {
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isPresent()) {
            model.addAttribute("book", optionalBook.get());
            model.addAttribute("referer", request.getHeader("referer"));

            return "book-details";
        }
        return "redirect:/book/list";
    }

}
