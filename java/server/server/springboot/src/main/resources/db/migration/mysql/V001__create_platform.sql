CREATE TABLE `platform` (
  `platform_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `short_name` varchar(128) NOT NULL,
  `storage_folder` varchar(128) NOT NULL,
  PRIMARY KEY (`platform_id`),
  UNIQUE KEY `UK_hp36t3hx9su23msu2p5qvukyh` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platform_alt_name` (
  `platform_alt_name_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alternate_name` varchar(255) NOT NULL,
  `platform_id` bigint(20) NOT NULL,
  PRIMARY KEY (`platform_alt_name_id`),
  UNIQUE KEY `PLATFORM_ALT_NAME_0001` (`platform_id`,`alternate_name`),
  UNIQUE KEY `UK_r3e1lapedbgwwkeep3pkquj3n` (`alternate_name`),
  CONSTRAINT `FK3d5xrpnxb4y2no478yhy3mvc6` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platform_artifact_file_import_history` (
  `platform_artifact_file_import_history_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `import_description` varchar(128) NOT NULL,
  `last_import_date` varchar(32) NOT NULL,
  `platform_id` bigint(20) NOT NULL,
  PRIMARY KEY (`platform_artifact_file_import_history_id`),
  UNIQUE KEY `PLATFORM_ARTIFACT_FILE_IMPORT_HISTORY_0001` (`platform_id`,`import_description`),
  CONSTRAINT `FKgrbcr34xkbxqaiawk9wmgdugg` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `artifact` (
  `platform_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`platform_id`),
  UNIQUE KEY `UK_g1ad0tae8u6y364oiwfi6am2n` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platform_artifact_file` (
  `platform_artifact_file_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_crc` varchar(8) NOT NULL,
  `file_md5` varchar(32) NOT NULL,
  `file_name` varchar(80) NOT NULL,
  `file_sha1` varchar(40) NOT NULL,
  `file_size` bigint(20) NOT NULL,
  `file_type` varchar(16) NOT NULL,
  `platform_id` bigint(20) NOT NULL,
  PRIMARY KEY (`platform_artifact_file_id`),
  UNIQUE KEY `PLATFORM_ARTIFACT_FILE_0001` (`platform_id`,`file_name`),
  KEY `PLATFORM_ARTIFACT_FILE_0002` (`platform_id`,`file_crc`),
  KEY `PLATFORM_ARTIFACT_FILE_0003` (`platform_id`,`file_md5`),
  KEY `PLATFORM_ARTIFACT_FILE_0004` (`platform_id`,`file_sha1`),
  CONSTRAINT `FKnboi57lbfouv9re6qgwi8rrnh` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platform_artifact_file_info` (
  `platform_artifact_file_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_info` varchar(255) NOT NULL,
  `platform_artifact_file_id` bigint(20) NOT NULL,
  PRIMARY KEY (`platform_artifact_file_info_id`),
  UNIQUE KEY `PLATFORM_ARTIFACT_FILE_INFO_0001` (`platform_artifact_file_id`,`file_info`),
  CONSTRAINT `FKoihuuqgsowblu5j5e0kxu4kvn` FOREIGN KEY (`platform_artifact_file_id`) REFERENCES `platform_artifact_file` (`platform_artifact_file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `platform_artifact_file_info_history` (
  `platform_artifact_file_info_history_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `platform_artifact_file_import_history_id` bigint(20) NOT NULL,
  `platform_artifact_file_info_id` bigint(20) NOT NULL,
  PRIMARY KEY (`platform_artifact_file_info_history_id`),
  UNIQUE KEY `PLATFORM_ARTIFACT_FILE_INFO_HISTORY_0001` (`platform_artifact_file_info_id`,`platform_artifact_file_import_history_id`),
  KEY `FK6i4e6brkksid4x2xnky3u5wtr` (`platform_artifact_file_import_history_id`),
  CONSTRAINT `FK6i4e6brkksid4x2xnky3u5wtr` FOREIGN KEY (`platform_artifact_file_import_history_id`) REFERENCES `platform_artifact_file_import_history` (`platform_artifact_file_import_history_id`),
  CONSTRAINT `FKmm7xl7dlyfbuesqc675v2g72r` FOREIGN KEY (`platform_artifact_file_info_id`) REFERENCES `platform_artifact_file_info` (`platform_artifact_file_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
