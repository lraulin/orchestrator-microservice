package com.smoothstack.lms.orchestratorservice.controller.microservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.smoothstack.lms.orchestratorservice.controller.AuthenticationController;
import com.smoothstack.lms.orchestratorservice.entity.Book;
import com.smoothstack.lms.orchestratorservice.entity.BookLoan;
import com.smoothstack.lms.orchestratorservice.entity.BookLoan.BookLoanId;
import com.smoothstack.lms.orchestratorservice.entity.LibraryBranch;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/lms/borrower")
public class BorrowerController {

    @Autowired
    RestTemplate restTemplate;

    private static final String baseUrl = "http://borrower-service/lms/borrower";
    private static final String checkOutUrl = "/borrowers/{borrowerId}/branches/{branchId}/books/{bookId}:checkout";
    private static final String checkInUrl = "/borrowers/{borrowerId}/branches/{branchId}/books/{bookId}:checkin";
    private static final String libraryBranchesUrl = "/branches";
    private static final String availableBooksNotCheckedOutUrl = "/borrowers/{borrowerId}/branches/{branchId}/available-books/";
    private static final String bookLoansUrl = "/branches/{branchId}/borrowers/{borrowerId}";
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    private static String fullUrl(String s) {
        return baseUrl + s;
    }

    @PostMapping(checkOutUrl)
    void checkOutBook(@PathVariable("bookId") long bookId, @PathVariable("branchId") long branchId,
            @PathVariable("borrowerId") long borrowerId) {
        logger.debug("request: bookId={}, borrowerId={}, branchId={}", bookId, borrowerId, branchId);
        BookLoanId id = new BookLoanId(bookId, borrowerId, branchId);
        this.restTemplate.postForObject(fullUrl(checkOutUrl), id, BookLoanId.class);
    }

    @PostMapping(checkInUrl)
    void checkInBook(@PathVariable("bookId") long bookId, @PathVariable("branchId") long branchId,
            @PathVariable("borrowerId") long borrowerId) {
        logger.debug("request: bookId={}, borrowerId={}, branchId={}", bookId, borrowerId, branchId);
        BookLoan.BookLoanId id = new BookLoan.BookLoanId(bookId, borrowerId, branchId);
        this.restTemplate.postForObject(fullUrl(checkInUrl), id, BookLoan.BookLoanId.class);
    }

    @GetMapping(libraryBranchesUrl)
    public LibraryBranch[] getLibraryBranches() {
        ResponseEntity<LibraryBranch[]> responseEntity = this.restTemplate.getForEntity(fullUrl(libraryBranchesUrl),
                LibraryBranch[].class);
        logger.debug("response: {}", responseEntity);
        return responseEntity.getBody();
    }

    @GetMapping(availableBooksNotCheckedOutUrl)
    public Book[] getAvailableBooksNotCheckedOut(@PathVariable("branchId") long branchId,
            @PathVariable("borrowerId") long borrowerId) {
        logger.debug("request: branchId: {}, borrowerId={}", branchId, borrowerId);
        ResponseEntity<Book[]> responseEntity = this.restTemplate.getForEntity(fullUrl(availableBooksNotCheckedOutUrl),
                Book[].class);
        logger.debug("response: {}", responseEntity);
        return responseEntity.getBody();
    }

    @GetMapping(bookLoansUrl)
    public BookLoan[] getBookLoans(@PathVariable("branchId") long branchId,
            @PathVariable("borrowerId") long borrowerId) {
        logger.debug("request: branchId: {}, borrowerId={}", branchId, borrowerId);
        ResponseEntity<BookLoan[]> responseEntity = this.restTemplate.getForEntity(fullUrl(bookLoansUrl),
                BookLoan[].class);
        logger.debug("response: {}", responseEntity);
        return responseEntity.getBody();
    }
}
