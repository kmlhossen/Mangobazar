CREATE TABLE IF NOT EXISTS CREATE TABLE `mangobazar`.`category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `mangobazar`.`product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `category_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES category(id)
  );