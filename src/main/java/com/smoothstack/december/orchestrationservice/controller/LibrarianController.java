package com.smoothstack.december.orchestrationservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.december.orchestrationservice.entity.Book;
import com.smoothstack.december.orchestrationservice.entity.BookCopy;
import com.smoothstack.december.orchestrationservice.entity.LibraryBranch;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String baseUrl = "http://localhost:8081/lms/librarian";

    @PutMapping("/book-copies")
    public void updateBookCopy(@RequestBody @Valid BookCopy bookCopy) {
        this.restTemplate.put(baseUrl + "/bookCopies", bookCopy);
    }

    @PostMapping("/bookCopies")
    public BookCopy createBookCopy(@RequestBody @Valid BookCopy bookCopy) {
        return this.restTemplate.postForObject(baseUrl + "/bookCopies", bookCopy, BookCopy.class);
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody @Valid Book book) {
        return this.restTemplate.postForObject(baseUrl + "/bookCopies", book, Book.class);
    }

    @PutMapping("/branches")
    public void updateLibraryBranch(@RequestBody @Valid LibraryBranch branch) {
        this.restTemplate.put(baseUrl + "/branches", branch);
    }

    @GetMapping("/books")
    public Book[] getBooks() {
        return this.restTemplate.getForObject(baseUrl + "/books", Book[].class);
    }

    @GetMapping("/branches")
    public LibraryBranch[] getLibraryBranches() {
        return this.restTemplate.getForObject(baseUrl + "/branches", LibraryBranch[].class);
    }

    @GetMapping("/bookCopies/{branchId}")
    public BookCopy[] getBookCopies(@PathVariable Long branchId) {
        return this.restTemplate.getForObject(baseUrl + "/bookCopies/" + branchId, BookCopy[].class);
    }

}
