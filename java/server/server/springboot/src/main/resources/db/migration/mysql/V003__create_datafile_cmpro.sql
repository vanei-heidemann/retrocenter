CREATE TABLE `cmpro_datafile` (
  `datafile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(400) DEFAULT NULL,
  `catalog` varchar(32) NOT NULL,
  `category` varchar(128) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `forcemerging` varchar(5) DEFAULT NULL,
  `forcezipping` varchar(3) DEFAULT NULL,
  `homepage` varchar(128) DEFAULT NULL,
  `name` varchar(160) NOT NULL,
  `url` varchar(128) DEFAULT NULL,
  `version` varchar(64) NOT NULL,
  PRIMARY KEY (`datafile_id`),
  UNIQUE KEY `CMPRO_DATAFILE_0001` (`name`,`catalog`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_customfield` (
  `cmpro_datafile_entity_datafile_id` bigint(20) NOT NULL,
  `custom_value` varchar(255) NOT NULL,
  `custom_key` varchar(160) NOT NULL,
  PRIMARY KEY (`cmpro_datafile_entity_datafile_id`,`custom_key`),
  CONSTRAINT `FKblehskaeuhsllhiixjn67lwen` FOREIGN KEY (`cmpro_datafile_entity_datafile_id`) REFERENCES `cmpro_datafile` (`datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_game` (
  `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cloneof` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `romof` varchar(255) DEFAULT NULL,
  `serial` varchar(64) DEFAULT NULL,
  `year` varchar(32) DEFAULT NULL,
  `datafile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `CMPRO_GAME_0001` (`datafile_id`,`name`),
  CONSTRAINT `FKkjo63xnwmgbrpsleh835gma1j` FOREIGN KEY (`datafile_id`) REFERENCES `cmpro_datafile` (`datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_gamerom` (
  `gamerom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crc` varchar(8) DEFAULT NULL,
  `flags` varchar(16) DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `region` varchar(64) DEFAULT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`gamerom_id`),
  UNIQUE KEY `CMPRO_GAMEROM_0001` (`game_id`,`name`,`size`,`crc`,`sha1`,`md5`,`region`,`flags`),
  CONSTRAINT `FK2r5oauoodindaba6abi6xov2b` FOREIGN KEY (`game_id`) REFERENCES `cmpro_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_resource` (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `year` varchar(32) DEFAULT NULL,
  `datafile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `CMPRO_RESOURCE_0001` (`datafile_id`,`name`),
  CONSTRAINT `FKph2ya9nruu2f9lgvn3l211fad` FOREIGN KEY (`datafile_id`) REFERENCES `cmpro_datafile` (`datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_resourcerom` (
  `resourcerom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crc` varchar(8) DEFAULT NULL,
  `flags` varchar(16) DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `region` varchar(64) DEFAULT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`resourcerom_id`),
  UNIQUE KEY `CMPRO_RESOURCEROM_0001` (`resource_id`,`name`,`size`,`crc`,`sha1`,`md5`,`region`,`flags`),
  CONSTRAINT `FKdc3m2ch7qbh0a6xcejpkwmvxx` FOREIGN KEY (`resource_id`) REFERENCES `cmpro_resource` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_disk` (
  `disk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `md5` varchar(32) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`disk_id`),
  UNIQUE KEY `CMPRO_DISK_0001` (`game_id`,`name`,`sha1`,`md5`),
  CONSTRAINT `FKtgm26qbq6n0du18ll5rar4lv` FOREIGN KEY (`game_id`) REFERENCES `cmpro_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_sample` (
  `sample_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sample` varchar(255) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sample_id`),
  UNIQUE KEY `CMPRO_SAMPLE_0001` (`game_id`,`sample`),
  CONSTRAINT `FKk1fgurftldr11j470gbbsmhn2` FOREIGN KEY (`game_id`) REFERENCES `cmpro_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cmpro_sampleof` (
  `sampleof_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sampleof` varchar(255) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sampleof_id`),
  UNIQUE KEY `CMPRO_SAMPLEOF_0001` (`game_id`,`sampleof`),
  CONSTRAINT `FK7e1c2vqivbcmqvxvesn8ox597` FOREIGN KEY (`game_id`) REFERENCES `cmpro_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
