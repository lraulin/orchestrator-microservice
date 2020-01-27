package com.smoothstack.december.orchestrationservice.controller;

import com.smoothstack.december.orchestrationservice.entity.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/lms/borrower")
public class AdministratorController {

    @Autowired
    RestTemplate restTemplate;

    private static final String baseUrl = "http://localhost:8085/lms/librarian";

    @NotNull
    @Contract(pure = true)
    private static String fullUrl(String s) {
        return baseUrl + s;
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable long id) {
        ResponseEntity<Author[]> responseEntity = restTemplate.getForEntity(fullUrl("/author/") + id, Author[].class);
    }

    @GetMapping("/authors")
    public Author[] getAuthors() {
        ResponseEntity<Author[]> responseEntity = restTemplate.getForEntity(fullUrl("/authors"), Author[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/authors")
    public void createAuthor(@RequestBody Author author) {
        restTemplate.postForObject(fullUrl("/authors"), author, Author.class);
    }

    @PutMapping("/author/{id}")
    public void updateAuthor(@PathVariable long id, @RequestBody Author author) {
        restTemplate.put(fullUrl("/author/") + id, author);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable long id) {
        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(fullUrl("/book/") + id, Book[].class);
    }

    @GetMapping("/books")
    public Book[] getBooks() {
        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(fullUrl("/books"), Book[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/books")
    public void createBook(@RequestBody Book book) {
        restTemplate.postForObject(fullUrl("/books"), book, Book.class);
    }

    @PutMapping("/book/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book book) {
        restTemplate.put(fullUrl("/book/") + id, book);
    }

    @DeleteMapping("/bookLoan/{id}")
    public void deleteBookLoan(@PathVariable long id) {
        ResponseEntity<BookLoan[]> responseEntity = restTemplate.getForEntity(fullUrl("/bookLoan/") + id,
                BookLoan[].class);
    }

    @GetMapping("/bookLoans")
    public BookLoan[] getBookLoans() {
        ResponseEntity<BookLoan[]> responseEntity = restTemplate.getForEntity(fullUrl("/bookLoans"), BookLoan[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/bookLoans")
    public void createBookLoan(@RequestBody BookLoan bookLoan) {
        restTemplate.postForObject(fullUrl("/bookLoans"), bookLoan, BookLoan.class);
    }

    @PutMapping("/bookLoan/{id}")
    public void updateBookLoan(@PathVariable long id, @RequestBody BookLoan bookLoan) {
        restTemplate.put(fullUrl("/bookLoan/") + id, bookLoan);
    }

    @DeleteMapping("/borrower/{id}")
    public void deleteBorrower(@PathVariable long id) {
        ResponseEntity<Borrower[]> responseEntity = restTemplate.getForEntity(fullUrl("/borrower/") + id,
                Borrower[].class);
    }

    @GetMapping("/borrowers")
    public Borrower[] getBorrowers() {
        ResponseEntity<Borrower[]> responseEntity = restTemplate.getForEntity(fullUrl("/borrowers"), Borrower[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/borrowers")
    public void createBorrower(@RequestBody Borrower borrower) {
        restTemplate.postForObject(fullUrl("/borrowers"), borrower, Borrower.class);
    }

    @PutMapping("/borrower/{id}")
    public void updateBorrower(@PathVariable long id, @RequestBody Borrower borrower) {
        restTemplate.put(fullUrl("/borrower/") + id, borrower);
    }

    @DeleteMapping("/genre/{id}")
    public void deleteGenre(@PathVariable long id) {
        ResponseEntity<Genre[]> responseEntity = restTemplate.getForEntity(fullUrl("/genre/") + id, Genre[].class);
    }

    @GetMapping("/genres")
    public Genre[] getGenres() {
        ResponseEntity<Genre[]> responseEntity = restTemplate.getForEntity(fullUrl("/genres"), Genre[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/genres")
    public void createGenre(@RequestBody Genre genre) {
        restTemplate.postForObject(fullUrl("/genres"), genre, Genre.class);
    }

    @PutMapping("/genre/{id}")
    public void updateGenre(@PathVariable long id, @RequestBody Genre genre) {
        restTemplate.put(fullUrl("/genre/") + id, genre);
    }

    @DeleteMapping("/libraryBranch/{id}")
    public void deleteLibraryBranch(@PathVariable long id) {
        ResponseEntity<LibraryBranch[]> responseEntity = restTemplate.getForEntity(fullUrl("/libraryBranch/") + id,
                LibraryBranch[].class);
    }

    @GetMapping("/libraryBranches")
    public LibraryBranch[] getLibraryBranches() {
        ResponseEntity<LibraryBranch[]> responseEntity = restTemplate.getForEntity(fullUrl("/libraryBranches"),
                LibraryBranch[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/libraryBranches")
    public void createLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        restTemplate.postForObject(fullUrl("/libraryBranches"), libraryBranch, LibraryBranch.class);
    }

    @PutMapping("/libraryBranch/{id}")
    public void updateLibraryBranch(@PathVariable long id, @RequestBody LibraryBranch libraryBranch) {
        restTemplate.put(fullUrl("/libraryBranch/") + id, libraryBranch);
    }

}
