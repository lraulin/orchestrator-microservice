package com.smoothstack.december.orchestrationservice.security.config;

public class RoleConstants {

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String LIBRARIAN_ROLE = "LIBRARIAN";
    public static final String BORROWER_ROLE = "BORROWER";

    public static final String PREFIXED_ADMIN_ROLE = "ROLE_ADMIN";
    public static final String PREFIXED_LIBRARIAN_ROLE = "ROLE_LIBRARIAN";
    public static final String PREFIXED_BORROWER_ROLE = "ROLE_BORROWER";

    public static final String ADMIN_DESCRIPTION = "Admin user - Has permission to create, read, update, and delete tasks for all entities.";
    public static final String LIBRARIAN_DESCRIPTION = "Librarian user - has permissions to create book copies, read book copies, update book copies, delete book copies, create books, get books, and update library branches.";
    public static final String BORROWER_DESCRIPTION = "Borrower user  - Has basic access for viewing books, and checking in and out books.";

}
