
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('1', 'Søborg', '2860');
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('2', 'Northrend', '1111');
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('3', 'Quel\'Thalas', '2222');
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('4', 'Elwyn', '3333');
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('5', 'Ishøj', '2635');
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('6', 'Holte', '2840');
INSERT INTO `CA2`.`CITYINFO` (`ID`, `CITY`, `ZIPCODE`) VALUES ('7', 'Lyngby', '2800');

INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('1', 'By the tree', 'Birch Road 5', '1');
INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('2', 'In the frozen wastes', 'Icecrown 1', '2');
INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('3', 'The Capitol', 'Stormwind', '4');
INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('4', 'In the tranquil forest', 'Windrunner Spire 1', '3');
INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('5', 'In the north!', 'Mammas Street 3', '6');
INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('6', 'In the south', 'City2 15', '5');
INSERT INTO `CA2`.`ADDRESS` (`ID`, `ADDITIONALINFO`, `STREET`, `cityinfo_id`) VALUES ('7', 'Intro area', 'Main Road 46', '7');


INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('1', 'rigmor@mail.dk', 'Rigmor', 'Atramedes', '1');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('2', 'zacharias@mail.dk', 'Lirekassen', 'Essakaril', '1');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('3', 'lich@king.dk', 'Arthas', 'Menethil', '2');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('4', 'lich@queen.dk', 'Sylvanas', 'Windrunner', '4');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('5', 'lich@king2.dk', 'Bolvar', 'Fordragon', '3');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('6', 'rúni@mail.fo', 'RÃºni', 'DankJerry', '6');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('7', 'hard@code.dk', 'Mr.', 'Hardcode', '5');
INSERT INTO `CA2`.`PERSON` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `address_id`) VALUES ('8', 'hvid@mail.dk', '46', 'Hvid', '7');


INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('1', 'Mobile', '11111111', '1');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('2', 'School', '22222222', '1');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('3', 'Mobile', '33333333', '2');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('4', 'Work', '44444444', '2');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('5', 'Car', '55555555', '2');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('6', 'Citadel', '66666666', '3');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('7', 'Secretary Nathanos Blightcaller', '77777777', '4');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('8', 'Stormwind', '88888888', '5');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('9', 'Citadel', '99999999', '5');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('10', 'Mobile', '00000000', '6');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('11', 'Work', '12345678', '6');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('12', 'School', '23456789', '6');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('13', 'Mobile', '34567891', '7');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('14', 'School', '45678912', '7');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('15', 'Mobile', '56789123', '8');
INSERT INTO `CA2`.`PHONE` (`ID`, `DESCRIPTION`, `NUMBER`, `person_id`) VALUES ('16', 'School', '67891234', '8');


INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('1', 'Finding and killing Dragons', 'Dragonslaying');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('2', 'Looking at interesting things', 'Sightseeing');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('3', 'Purging the city of Stratholme', 'Worldsaving');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('4', 'Keeping the Scourge at bay', 'Real Worldsaving');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('5', 'Seeking vengance', 'Becomming Lich Queen');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('6', 'Hunting Monsters', 'Monster Hunter');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('7', 'Unmake the Unmaker', 'Being 46. best at killing Argus');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('8', 'Superior at everything git', 'Git-mastering');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('9', 'Would prefer to code exclusively in Assembly', 'Oldschool coding');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('10', 'Glory to Arstotzka!', 'Passport-checking');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('11', 'Rhok\'delar', 'Wielding Longbow of the Ancient Keepers');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('12', 'Over and over again', 'Dying');
INSERT INTO `CA2`.`HOBBY` (`ID`, `DESCRIPTION`, `NAME`) VALUES ('13', '#noJoke....', 'JPA');


INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('1', '1');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('2', '1');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('10', '1');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('13', '1');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('1', '2');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('11', '2');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('12', '2');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('3', '3');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('5', '4');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('12', '4');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('4', '5');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('8', '6');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('13', '6');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('6', '7');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('9', '7');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('13', '7');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('1', '8');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('7', '8');
INSERT INTO `CA2`.`PERSON_HOBBY` (`hobbies_ID`, `persons_ID`) VALUES ('12', '8');


