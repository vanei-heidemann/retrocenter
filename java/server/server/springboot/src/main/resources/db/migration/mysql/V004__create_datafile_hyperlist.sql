CREATE TABLE `hyperlist_datafile` (
  `datafile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exporter_version` varchar(255) DEFAULT NULL,
  `last_update` varchar(16) NOT NULL,
  `name` varchar(64) NOT NULL,
  `version` varchar(64) NOT NULL,
  PRIMARY KEY (`datafile_id`),
  UNIQUE KEY `HYPERLIST_DATAFILE_0001` (`name`,`last_update`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hyperlist_game` (
  `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cloneof` varchar(255) DEFAULT NULL,
  `crc` varchar(8) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `genre` varchar(128) DEFAULT NULL,
  `game_image` varchar(16) DEFAULT NULL,
  `game_index` varchar(16) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  `datafile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `HYPERLIST_GAME_0001` (`game_id`,`name`,`game_index`,`game_image`),
  KEY `FKg1big9cxcgs89jqunx3ceepv6` (`datafile_id`),
  CONSTRAINT `FKg1big9cxcgs89jqunx3ceepv6` FOREIGN KEY (`datafile_id`) REFERENCES `hyperlist_datafile` (`datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
