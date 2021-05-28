DROP TABLE IF EXISTS `moviedb`.`movie`;
CREATE TABLE `moviedb`.`movie` (
`imdb_id` VARCHAR(50) NOT NULL,
`title` VARCHAR(50) NOT NULL,
`year` VARCHAR(20) NULL DEFAULT NULL,
`genre` VARCHAR(200) NULL DEFAULT NULL,
`director` VARCHAR(20) NULL DEFAULT NULL,
`imdb_rating` DECIMAL(10,2) NULL DEFAULT NULL,
`imdb_votes` VARCHAR(50) NULL DEFAULT NULL,
primary key (`imdb_id`));

DROP TABLE IF EXISTS `moviedb`.`user`;
CREATE TABLE `moviedb`.`user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`));


INSERT INTO `moviedb`.`user` (`id`, `username`, `password`) VALUES ('1', 'user', '$2a$04$7D3oWLnPy6VvzFn2WkEm9en6dQ8dR5b1hJ/1qBTCG2eCTQ.TGxjIu');
INSERT INTO `moviedb`.`user` (`id`, `username`, `password`) VALUES ('2', 'root', '$2a$04$Q0ZPdZjDQdnErYyo/MO0jeLfK/Q4fAF4edKCKKXbFoRLda4RWV86m');

