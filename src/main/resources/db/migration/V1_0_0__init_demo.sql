-- CREATE TABLE IF NOT EXISTS `varadehaldamine`.`asset` (
-- `id` INT NOT NULL AUTO_INCREMENT ,
-- `name` VARCHAR(255) NOT NULL ,
-- `value_id` INT NOT NULL ,
-- `description` VARCHAR(255) NULL ,
-- `subclass` INT NOT NULL ,
-- `active` TINYINT NOT NULL ,
-- `address_id` INT NULL DEFAULT NULL ,
-- `user_id` INT NULL DEFAULT NULL ,
-- `owner_id` INT NOT NULL ,
-- `accured` INT NOT NULL ,
-- `expiration_date` DATE NULL ,
-- `delicate_condition` TINYINT NOT NULL ,
-- `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ,
-- `modified_at` DATETIME on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
--  PRIMARY KEY (`id`)) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS demo_table (
    id SERIAL PRIMARY KEY,
    alt_id VARCHAR(52),
    created timestamp
);