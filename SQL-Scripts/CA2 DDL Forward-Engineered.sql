
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema CA2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CA2
-- -----------------------------------------------------
DROP SCHEMA if EXISTS`CA2`;
CREATE SCHEMA IF NOT EXISTS `CA2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `CA2` ;

-- -----------------------------------------------------
-- Table `CA2`.`CITYINFO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`CITYINFO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `CITY` VARCHAR(255) NULL DEFAULT NULL,
  `ZIPCODE` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA2`.`ADDRESS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`ADDRESS` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ADDITIONALINFO` VARCHAR(255) NULL DEFAULT NULL,
  `STREET` VARCHAR(255) NULL DEFAULT NULL,
  `cityinfo_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_ADDRESS_cityinfo_id` (`cityinfo_id` ASC) VISIBLE,
  CONSTRAINT `FK_ADDRESS_cityinfo_id`
    FOREIGN KEY (`cityinfo_id`)
    REFERENCES `CA2`.`CITYINFO` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA2`.`HOBBY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`HOBBY` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` VARCHAR(255) NULL DEFAULT NULL,
  `NAME` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA2`.`PERSON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`PERSON` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` VARCHAR(255) NULL DEFAULT NULL,
  `FIRSTNAME` VARCHAR(255) NULL DEFAULT NULL,
  `LASTNAME` VARCHAR(255) NULL DEFAULT NULL,
  `address_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_PERSON_address_id` (`address_id` ASC) VISIBLE,
  CONSTRAINT `FK_PERSON_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `CA2`.`ADDRESS` (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 0
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA2`.`PERSON_HOBBY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`PERSON_HOBBY` (
  `hobbies_ID` INT(11) NOT NULL,
  `persons_ID` INT(11) NOT NULL,
  PRIMARY KEY (`hobbies_ID`, `persons_ID`),
  INDEX `FK_PERSON_HOBBY_persons_ID` (`persons_ID` ASC) VISIBLE,
  CONSTRAINT `FK_PERSON_HOBBY_hobbies_ID`
    FOREIGN KEY (`hobbies_ID`)
    REFERENCES `CA2`.`HOBBY` (`ID`),
  CONSTRAINT `FK_PERSON_HOBBY_persons_ID`
    FOREIGN KEY (`persons_ID`)
    REFERENCES `CA2`.`PERSON` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `CA2`.`PHONE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CA2`.`PHONE` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` VARCHAR(255) NULL DEFAULT NULL,
  `NUMBER` INT(11) NULL DEFAULT NULL,
  `person_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `FK_PHONE_person_id` (`person_id` ASC) VISIBLE,
  CONSTRAINT `FK_PHONE_person_id`
    FOREIGN KEY (`person_id`)
    REFERENCES `CA2`.`PERSON` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
