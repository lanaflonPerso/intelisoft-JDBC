SET global time_zone = `+02:00`;

CREATE SCHEMA if not exists `auto_service_db` DEFAULT CHARACTER SET utf8;

use `auto_service_db`;

CREATE TABLE `consumer` (
	`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `firstName` VARCHAR(40) NOT NULL,
    `lastName` VARCHAR(40) NOT NULL,
    `birthDate` DATETIME NOT NULL,
    `country` VARCHAR(40) NOT NULL,
    `city` VARCHAR(40) NOT NULL
);

CREATE TABLE `car` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `maker` VARCHAR(40) NOT NULL,
    `model` VARCHAR(40) NOT NULL,
    `productionYear` INT NOT NULL,
    `color` VARCHAR(40) NOT NULL,
    `engineType` VARCHAR(40) NOT NULL,
    `odometer` INT NOT NULL,
    `consumer_id` BIGINT,
    FOREIGN KEY (`consumer_id`)
        REFERENCES `consumer` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);