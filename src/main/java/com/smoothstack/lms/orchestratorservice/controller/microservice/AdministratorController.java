package com.smoothstack.lms.orchestratorservice.controller.microservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestratorservice.entity.Author;
import com.smoothstack.lms.orchestratorservice.entity.Book;
import com.smoothstack.lms.orchestratorservice.entity.BookLoan;
import com.smoothstack.lms.orchestratorservice.entity.Borrower;
import com.smoothstack.lms.orchestratorservice.entity.Genre;
import com.smoothstack.lms.orchestratorservice.entity.LibraryBranch;

@RestController
@RequestMapping("/lms/admin")
public class AdministratorController {

    @Autowired
    RestTemplate restTemplate;

    private static final String baseUrl = "http://admin-service/lms/admin";

    private static String fullUrl(String s) {
        return baseUrl + s;
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/author/") + id, Author[].class);
    }

    @GetMapping("/authors")
    public Author[] getAuthors() {
        ResponseEntity<Author[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/authors"), Author[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/authors")
    public void createAuthor(@RequestBody Author author) {
        this.restTemplate.postForObject(fullUrl("/authors"), author, Author.class);
    }

    @PutMapping("/author/{id}")
    public void updateAuthor(@PathVariable long id, @RequestBody Author author) {
        this.restTemplate.put(fullUrl("/author/") + id, author);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/book/") + id, Book[].class);
    }

    @GetMapping("/books")
    public Book[] getBooks() {
        ResponseEntity<Book[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/books"), Book[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book book) {
        this.restTemplate.postForObject(fullUrl("/books"), book, Book.class);
    }

    @PutMapping("/book/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book book) {
        this.restTemplate.put(fullUrl("/book/") + id, book);
    }

    @DeleteMapping("/bookLoan/{id}")
    public void deleteBookLoan(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/bookLoan/") + id, BookLoan[].class);
    }

    @GetMapping("/bookLoans")
    public BookLoan[] getBookLoans() {
        ResponseEntity<BookLoan[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/bookLoans"),
                BookLoan[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/bookLoans")
    public void createBookLoan(@RequestBody BookLoan bookLoan) {
        this.restTemplate.postForObject(fullUrl("/bookLoans"), bookLoan, BookLoan.class);
    }

    @PutMapping("/bookLoan/{id}")
    public void updateBookLoan(@PathVariable long id, @RequestBody BookLoan bookLoan) {
        this.restTemplate.put(fullUrl("/bookLoan/") + id, bookLoan);
    }

    @DeleteMapping("/borrower/{id}")
    public void deleteBorrower(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/borrower/") + id, Borrower[].class);
    }

    @GetMapping("/borrowers")
    public Borrower[] getBorrowers() {
        ResponseEntity<Borrower[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/borrowers"),
                Borrower[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/borrowers")
    public void createBorrower(@RequestBody Borrower borrower) {
        this.restTemplate.postForObject(fullUrl("/borrowers"), borrower, Borrower.class);
    }

    @PutMapping("/borrower/{id}")
    public void updateBorrower(@PathVariable long id, @RequestBody Borrower borrower) {
        this.restTemplate.put(fullUrl("/borrower/") + id, borrower);
    }

    @DeleteMapping("/genre/{id}")
    public void deleteGenre(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/genre/") + id, Genre[].class);
    }

    @GetMapping("/genres")
    public Genre[] getGenres() {
        ResponseEntity<Genre[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/genres"), Genre[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/genres")
    public void createGenre(@RequestBody Genre genre) {
        this.restTemplate.postForObject(fullUrl("/genres"), genre, Genre.class);
    }

    @PutMapping("/genre/{id}")
    public void updateGenre(@PathVariable long id, @RequestBody Genre genre) {
        this.restTemplate.put(fullUrl("/genre/") + id, genre);
    }

    @DeleteMapping("/branch/{id}")
    public void deleteLibraryBranch(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/libraryBranch/") + id, LibraryBranch[].class);
    }

    @GetMapping("/branches")
    public LibraryBranch[] getLibraryBranches() {
        ResponseEntity<LibraryBranch[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/libraryBranches"),
                LibraryBranch[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/branches")
    public void createLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        this.restTemplate.postForObject(fullUrl("/libraryBranches"), libraryBranch, LibraryBranch.class);
    }

    @PutMapping("/branch/{id}")
    public void updateLibraryBranch(@PathVariable long id, @RequestBody LibraryBranch libraryBranch) {
        this.restTemplate.put(fullUrl("/libraryBranch/") + id, libraryBranch);
    }

}
