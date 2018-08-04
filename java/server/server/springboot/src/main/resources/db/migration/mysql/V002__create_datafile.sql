CREATE TABLE `datafile` (
  `datafile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(400) DEFAULT NULL,
  `catalog` varchar(32) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `homepage` varchar(128) DEFAULT NULL,
  `name` varchar(160) NOT NULL,
  `url` varchar(128) DEFAULT NULL,
  `version` varchar(64) NOT NULL,
  PRIMARY KEY (`datafile_id`),
  UNIQUE KEY `DATAFILE_0001` (`name`,`catalog`,`version`),
  KEY `DATAFILE_0002` (`catalog`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `datafile_artifact` (
  `artifact_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `year` varchar(32) DEFAULT NULL,
  `datafile_id` bigint(20) NOT NULL,
  PRIMARY KEY (`artifact_id`),
  UNIQUE KEY `DATAFILE_ARTIFACT_0001` (`datafile_id`,`name`),
  CONSTRAINT `FKp2yk8mq9uxdv4kswb9cupldfv` FOREIGN KEY (`datafile_id`) REFERENCES `datafile` (`datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `datafile_artifact_field` (
  `datafile_artifact_entity_artifact_id` bigint(20) NOT NULL,
  `field_value` varchar(255) DEFAULT NULL,
  `field_key` varchar(128) NOT NULL,
  PRIMARY KEY (`datafile_artifact_entity_artifact_id`,`field_key`),
  CONSTRAINT `FKcfoywwwt9mlspdgtaenah9s5f` FOREIGN KEY (`datafile_artifact_entity_artifact_id`) REFERENCES `datafile_artifact` (`artifact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `datafile_artifactfile` (
  `artifactfile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crc` varchar(8) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `size` varchar(16) DEFAULT NULL,
  `file_type` varchar(16) NOT NULL,
  `artifact_id` bigint(20) NOT NULL,
  PRIMARY KEY (`artifactfile_id`),
  KEY `DATAFILE_ARTIFACTFILE_0001` (`artifact_id`,`file_type`,`name`,`crc`,`sha1`,`md5`),
  CONSTRAINT `FKod1gn89bn0rn8ao7bct6ladxh` FOREIGN KEY (`artifact_id`) REFERENCES `datafile_artifact` (`artifact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `datafile_artifactfile_field` (
  `datafile_artifact_file_entity_artifactfile_id` bigint(20) NOT NULL,
  `field_value` varchar(255) DEFAULT NULL,
  `field_key` varchar(128) NOT NULL,
  PRIMARY KEY (`datafile_artifact_file_entity_artifactfile_id`,`field_key`),
  CONSTRAINT `FKyxxvg9t2krgxuukjs13eaj9k` FOREIGN KEY (`datafile_artifact_file_entity_artifactfile_id`) REFERENCES `datafile_artifactfile` (`artifactfile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `datafile_release` (
  `release_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(3) DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL,
  `language` varchar(128) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `region` varchar(128) DEFAULT NULL,
  `artifact_id` bigint(20) NOT NULL,
  PRIMARY KEY (`release_id`),
  UNIQUE KEY `DATAFILE_RELEASE_0001` (`artifact_id`,`name`,`region`,`language`,`date`,`isdefault`),
  CONSTRAINT `FK13ukietsyib5i9n7nj0vr6yox` FOREIGN KEY (`artifact_id`) REFERENCES `datafile_artifact` (`artifact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
