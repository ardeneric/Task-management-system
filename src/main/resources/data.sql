INSERT INTO `users` (username, role, email, password)
VALUES ('ardeneric', 'ADMIN', 'ardeneric77@gmail.com', '$2y$08$DRkXSlN0BY5UBYJJxsBwueVPc/dLknxNuT3YQ13s31XP1/hBJTtFq');
VALUES ('user', 'USER', 'user@gmail.com', '$2y$10$agBOP4M9oJe81lqEGbthz.5aIPevM5SOrOOQ5CrD6cdmeN0iW5pH.');

-- Insert data into tasks table
INSERT INTO Task (title, description, priority, status, due_date)
VALUES ('Task 1', 'Description for Task 1', 'HIGH', 'IN_PROGRESS', '2024-05-01'),
       ('Task 2', 'Description for Task 2', 'LOW', 'TODO', '2024-05-02'),
       ('Task 3', 'Description for Task 3', 'MEDIUM', 'DONE', '2024-05-03'),
       ('Task 4', 'Description for Task 4', 'HIGH', 'IN_PROGRESS', '2024-05-04'),
       ('Task 5', 'Description for Task 5', 'LOW', 'TODO', '2024-05-05'),
       ('Task 6', 'Description for Task 6', 'MEDIUM', 'DONE', '2024-05-06'),
       ('Task 7', 'Description for Task 7', 'HIGH', 'IN_PROGRESS', '2024-05-07'),
       ('Task 8', 'Description for Task 8', 'LOW', 'TODO', '2024-05-08'),
       ('Task 9', 'Description for Task 9', 'MEDIUM', 'DONE', '2024-05-09'),
       ('Task 10', 'Description for Task 10', 'HIGH', 'IN_PROGRESS', '2024-05-10'),
       ('Task 11', 'Description for Task 11', 'LOW', 'TODO', '2024-05-11'),
       ('Task 12', 'Description for Task 12', 'MEDIUM', 'DONE', '2024-05-12'),
       ('Task 13', 'Description for Task 13', 'HIGH', 'IN_PROGRESS', '2024-05-13'),
       ('Task 14', 'Description for Task 14', 'LOW', 'TODO', '2024-05-14'),
       ('Task 15', 'Description for Task 15', 'MEDIUM', 'DONE', '2024-05-15'),
       ('Task 16', 'Description for Task 16', 'HIGH', 'IN_PROGRESS', '2024-05-16'),
       ('Task 17', 'Description for Task 17', 'LOW', 'TODO', '2024-05-17'),
       ('Task 18', 'Description for Task 18', 'MEDIUM', 'DONE', '2024-05-18'),
       ('Task 19', 'Description for Task 19', 'HIGH', 'IN_PROGRESS', '2024-05-19'),
       ('Task 20', 'Description for Task 20', 'LOW', 'TODO', '2024-05-20');

