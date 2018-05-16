
CREATE TABLE  `AUTENTIA`.`Greeting` (
	`id` int unsigned NOT NULL auto_increment,
	`message` varchar(128) NOT NULL,
	PRIMARY KEY  (`id`)
) engine=innodb DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
