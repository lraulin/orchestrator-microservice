package com.smoothstack.lms.orchestratorservice.controller.microservice;

import com.smoothstack.lms.orchestratorservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
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

    @DeleteMapping("/book-loan/{id}")
    public void deleteBookLoan(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/book-loan/") + id, BookLoan[].class);
    }

    @GetMapping("/book-loans")
    public BookLoan[] getBookLoans() {
        ResponseEntity<BookLoan[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/book-loans"),
                BookLoan[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/book-loans")
    public void createBookLoan(@RequestBody BookLoan bookLoan) {
        this.restTemplate.postForObject(fullUrl("/book-loans"), bookLoan, BookLoan.class);
    }

    @PutMapping("/book-loan/{id}")
    public void updateBookLoan(@PathVariable long id, @RequestBody BookLoan bookLoan) {
        this.restTemplate.put(fullUrl("/book-loan/") + id, bookLoan);
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
        this.restTemplate.getForEntity(fullUrl("/branch/") + id, LibraryBranch[].class);
    }

    @GetMapping("/branches")
    public LibraryBranch[] getLibraryBranches() {
        ResponseEntity<LibraryBranch[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/branches"),
                LibraryBranch[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/branches")
    public void createLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        this.restTemplate.postForObject(fullUrl("/branches"), libraryBranch, LibraryBranch.class);
    }

    @PutMapping("/branch/{id}")
    public void updateLibraryBranch(@PathVariable long id, @RequestBody LibraryBranch libraryBranch) {
        this.restTemplate.put(fullUrl("/branch/") + id, libraryBranch);
    }

    @DeleteMapping("/publisher/{id}")
    public void deletePublisher(@PathVariable long id) {
        this.restTemplate.getForEntity(fullUrl("/publisher/") + id, Publisher[].class);
    }

    @GetMapping("/publishers")
    public Publisher[] getPublishers() {
        ResponseEntity<Publisher[]> responseEntity = this.restTemplate.getForEntity(fullUrl("/publishers"),
                Publisher[].class);
        return responseEntity.getBody();
    }

    @PostMapping("/publishers")
    public void createPublisher(@RequestBody Publisher publisher) {
        this.restTemplate.postForObject(fullUrl("/publishers"), publisher, Publisher.class);
    }

    @PutMapping("/publisher/{id}")
    public void updatePublisher(@PathVariable long id, @RequestBody Publisher publisher) {
        this.restTemplate.put(fullUrl("/publisher/") + id, publisher);
    }

}
