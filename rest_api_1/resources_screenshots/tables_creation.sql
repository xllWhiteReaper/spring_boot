-- CREATE DATABASE user;
USE `user`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE user(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  email VARCHAR(255)
);

INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`) VALUES 
	(1, 'A-Cobra', 'Github', 'fakeEmail@gmail.com'),
	(2, 'xllWhiteReaper', 'Github', 'fakeEmail2@gmail.com'),
	(3, 'User3', 'LastName3', 'fakeEmail3@gmail.com'),
	(4, 'User4', 'LastName4', 'fakeEmail4@gmail.com')
;

SELECT * FROM `user`;