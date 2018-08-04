CREATE TABLE `logiqx_datafile` (
  `datafile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(400) DEFAULT NULL,
  `biosmode` varchar(8) DEFAULT NULL,
  `build` varchar(16) DEFAULT NULL,
  `catalog` varchar(32) NOT NULL,
  `category` varchar(128) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `debug` varchar(3) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `forcemerging` varchar(5) DEFAULT NULL,
  `forcenodump` varchar(8) DEFAULT NULL,
  `forcepacking` varchar(3) DEFAULT NULL,
  `header` varchar(64) DEFAULT NULL,
  `homepage` varchar(128) DEFAULT NULL,
  `lockbiosmode` varchar(3) DEFAULT NULL,
  `lockrommode` varchar(3) DEFAULT NULL,
  `locksamplemode` varchar(3) DEFAULT NULL,
  `name` varchar(160) NOT NULL,
  `plugin` varchar(64) DEFAULT NULL,
  `rommode` varchar(8) DEFAULT NULL,
  `samplemode` varchar(8) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `version` varchar(64) NOT NULL,
  PRIMARY KEY (`datafile_id`),
  UNIQUE KEY `LOGIQX_DATAFILE_0001` (`name`,`catalog`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_game` (
  `game_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `board` varchar(64) DEFAULT NULL,
  `cloneof` varchar(16) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isbios` varchar(3) DEFAULT NULL,
  `manufacturer` varchar(80) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `rebuildto` varchar(64) DEFAULT NULL,
  `romof` varchar(16) DEFAULT NULL,
  `sampleof` varchar(32) DEFAULT NULL,
  `sourcefile` varchar(48) DEFAULT NULL,
  `year` varchar(8) DEFAULT NULL,
  `datafile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`game_id`),
  UNIQUE KEY `LOGIQX_GAME_0001` (`datafile_id`,`name`),
  CONSTRAINT `FKjcx1pjpad1cu0ofi972is4pwc` FOREIGN KEY (`datafile_id`) REFERENCES `logiqx_datafile` (`datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_release` (
  `release_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(3) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `language` varchar(40) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `region` varchar(40) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`release_id`),
  UNIQUE KEY `LOGIQX_RELEASE_0001` (`game_id`,`name`,`region`,`language`,`date`,`isdefault`),
  CONSTRAINT `FKik3do05b5s7l6l7c1p5fcp4fl` FOREIGN KEY (`game_id`) REFERENCES `logiqx_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_archive` (
  `archive_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`archive_id`),
  UNIQUE KEY `LOGIQX_ARCHIVE_0001` (`game_id`,`name`),
  CONSTRAINT `FKsdvp00vwr0j9qi4wc0cv30atg` FOREIGN KEY (`game_id`) REFERENCES `logiqx_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_biosset` (
  `biosset_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(3) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`biosset_id`),
  UNIQUE KEY `LOGIQX_BIOSSET_0001` (`game_id`,`name`),
  CONSTRAINT `FK87xyk297ba5ehjbm60bb46g35` FOREIGN KEY (`game_id`) REFERENCES `logiqx_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_disk` (
  `disk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `md5` varchar(32) DEFAULT NULL,
  `merge` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `diskstatus` varchar(8) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`disk_id`),
  UNIQUE KEY `LOGIQX_DISK_0001` (`game_id`,`name`,`sha1`,`md5`),
  CONSTRAINT `FK317ic8v4omem2o0yal0cwy01t` FOREIGN KEY (`game_id`) REFERENCES `logiqx_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_rom` (
  `rom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crc` varchar(8) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `merge` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `romstatus` varchar(8) DEFAULT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rom_id`),
  UNIQUE KEY `LOGIQX_ROM_0001` (`game_id`,`name`,`size`,`crc`,`sha1`,`md5`),
  CONSTRAINT `FKb6kyk5yhv5fesgyqbesqammf6` FOREIGN KEY (`game_id`) REFERENCES `logiqx_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `logiqx_sample` (
  `sample_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `game_id` bigint(20) NOT NULL,
  PRIMARY KEY (`sample_id`),
  UNIQUE KEY `LOGIQX_SAMPLE_0001` (`game_id`,`name`),
  CONSTRAINT `FKs0v8ah9one2br4lwvubqiadxr` FOREIGN KEY (`game_id`) REFERENCES `logiqx_game` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
