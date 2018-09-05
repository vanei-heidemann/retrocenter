ALTER TABLE `datafile` ADD COLUMN `platform_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `datafile` ADD CONSTRAINT `FK_datafile_platform` FOREIGN KEY (`platform_id`) REFERENCES `platform` (`platform_id`) ON DELETE SET NULL ON UPDATE SET NULL;
