INSERT INTO role (name, description) VALUES
    ("ROLE_ADMIN", "Admin user - Has permission to create, read, update, and delete tasks for all entities."),
    ("ROLE_LIBRARIAN", "Librarian user - has permissions to create book copies, read book copies, update book copies, delete book copies, create books, get books, and update library branches."),
    ("ROLE_BORROWER", "Borrower user  - Has basic access for viewing books, and checking in and out books.");

-- password is "password"
INSERT INTO user (username, password) VALUES("admin", "$2a$10$Pg82j2E7r1TX8Ipn7gDp7.9qf9nqw2dJv8C5wks.GZi4/Kp4aVwJi");
INSERT INTO user_roles (user_id, role_id) VALUES
    (1, 1),
    (1, 2),
    (1, 3);