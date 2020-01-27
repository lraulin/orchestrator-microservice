package com.smoothstack.december.orchestrationservice.controller.microservice;

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

    @PostMapping("/book-copies")
    public void createBookCopy(@RequestBody BookCopy bookCopy) {
        this.restTemplate.postForObject(baseUrl + "/book-copies", bookCopy, BookCopy.class);
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book book) {
        this.restTemplate.postForObject(baseUrl + "/books", book, Book.class);
    }

    @GetMapping("/books")
    public Book[] getBooks() {
        return this.restTemplate.getForObject(baseUrl + "/books", Book[].class);
    }

    @GetMapping("/branches")
    public LibraryBranch[] getLibraryBranches() {
        return this.restTemplate.getForObject(baseUrl + "/branches", LibraryBranch[].class);
    }

    @GetMapping("/book-copies/branches/{branchId}")
    public BookCopy[] getBookCopies(@PathVariable Long branchId) {
        return this.restTemplate.getForObject(baseUrl + "/book-copies/branches/" + branchId, BookCopy[].class);
    }

    @PutMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public void updateBookCopy(@PathVariable Long bookId, @PathVariable Long branchId, @RequestBody BookCopy bookCopy) {
        this.restTemplate.put(baseUrl + "/book-copies/books/" + bookId + "/branches/" + branchId, bookCopy);
    }

    @PutMapping("/branches/{branchId}")
    public void updateLibraryBranch(@PathVariable Long branchId, @RequestBody LibraryBranch branch) {
        this.restTemplate.put(baseUrl + "/branches/" + branchId, branch);
    }

}
