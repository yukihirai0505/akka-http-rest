CREATE TABLE `database-name`.`tokens` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `token` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`));
ALTER TABLE `database-name`.`tokens` ADD INDEX `USER_FK_idx` (`user_id` ASC);
ALTER TABLE `database-name`.`tokens`
ADD CONSTRAINT `USER_FK` FOREIGN KEY (`user_id`)
REFERENCES `database-name`.`users` (`id`)
ON DELETE CASCADE
ON UPDATE RESTRICT;