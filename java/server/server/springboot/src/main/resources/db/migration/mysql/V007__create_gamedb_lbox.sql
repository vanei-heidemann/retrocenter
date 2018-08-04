CREATE TABLE `lbox_genre` (
  `lbox_genre_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`lbox_genre_id`),
  UNIQUE KEY `UK_pjiprgmqm14k2m9ltfp75k4tx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_region` (
  `lbox_region_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`lbox_region_id`),
  UNIQUE KEY `UK_9bmkgltqs0tvfv7gqlo5fqius` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_company` (
  `lbox_company_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`lbox_company_id`),
  UNIQUE KEY `UK_o16g1rgt984mr4vcjsjbrdcvo` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_platform` (
  `lbox_platform_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `release_date` varchar(32) DEFAULT NULL,
  `developer_id` bigint(20) DEFAULT NULL,
  `manufacturer_id` bigint(20) DEFAULT NULL,
  `platform_id` bigint(20) NOT NULL,
  PRIMARY KEY (`lbox_platform_id`),
  UNIQUE KEY `UK_dm8oxtrp745n52pm06f16v5f1` (`name`),
  KEY `FKbxdyg0bmra0pyndeye8onbsl0` (`developer_id`),
  KEY `FKdvq8piyhhv6senicjhrh9rr44` (`manufacturer_id`),
  CONSTRAINT `FKbxdyg0bmra0pyndeye8onbsl0` FOREIGN KEY (`developer_id`) REFERENCES `lbox_company` (`lbox_company_id`),
  CONSTRAINT `FKdvq8piyhhv6senicjhrh9rr44` FOREIGN KEY (`manufacturer_id`) REFERENCES `lbox_company` (`lbox_company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_platform_alt_name` (
  `lbox_platform_id` bigint(20) NOT NULL,
  `alternate_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`lbox_platform_id`,`alternate_name`),
  CONSTRAINT `FK3kdpcjn3rpkru67f6ni43ee2y` FOREIGN KEY (`lbox_platform_id`) REFERENCES `lbox_platform` (`lbox_platform_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_datafile` (
  `lbox_datafile_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` varchar(16) NOT NULL,
  PRIMARY KEY (`lbox_datafile_id`),
  UNIQUE KEY `UK_n2jwnn8gu6v5j42hy26d8wtmh` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_datafile_genre` (
  `datafile_genre_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbox_datafile_id` bigint(20) NOT NULL,
  `lbox_genre_id` bigint(20) NOT NULL,
  PRIMARY KEY (`datafile_genre_id`),
  UNIQUE KEY `UKksyiabjboskb83m7gc130d40x` (`lbox_datafile_id`,`lbox_genre_id`),
  KEY `FKe5x1sx83h4nbjx24gsrxyensr` (`lbox_genre_id`),
  CONSTRAINT `FKe5x1sx83h4nbjx24gsrxyensr` FOREIGN KEY (`lbox_genre_id`) REFERENCES `lbox_genre` (`lbox_genre_id`),
  CONSTRAINT `FKrl1hubf1r50bl29f5m3ydrged` FOREIGN KEY (`lbox_datafile_id`) REFERENCES `lbox_datafile` (`lbox_datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_datafile_platform` (
  `datafile_platform_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbox_datafile_id` bigint(20) NOT NULL,
  `lbox_platform_id` bigint(20) NOT NULL,
  PRIMARY KEY (`datafile_platform_id`),
  UNIQUE KEY `UKdgvtbtbbptp4arshv0iv7658m` (`lbox_datafile_id`,`lbox_platform_id`),
  KEY `FK1bc9blx1j1k6xywsson5ig5da` (`lbox_platform_id`),
  CONSTRAINT `FK1bc9blx1j1k6xywsson5ig5da` FOREIGN KEY (`lbox_platform_id`) REFERENCES `lbox_platform` (`lbox_platform_id`),
  CONSTRAINT `FK9sfbfgkkvv4njq2709vwfepqw` FOREIGN KEY (`lbox_datafile_id`) REFERENCES `lbox_datafile` (`lbox_datafile_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_datafile_region` (
  `datafile_region_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbox_datafile_id` bigint(20) NOT NULL,
  `lbox_region_id` bigint(20) NOT NULL,
  PRIMARY KEY (`datafile_region_id`),
  UNIQUE KEY `UKg4l6u8vgge6qr8mnl66l54694` (`lbox_datafile_id`,`lbox_region_id`),
  UNIQUE KEY `UKp3hvf42nqrg8xapuwlsj6l93w` (`lbox_datafile_id`,`lbox_region_id`),
  KEY `FKmfjtb1i3scyfw0htyctcfn75i` (`lbox_region_id`),
  CONSTRAINT `FK5e6na6a2xxiv1fife640t0xyf` FOREIGN KEY (`lbox_datafile_id`) REFERENCES `lbox_datafile` (`lbox_datafile_id`),
  CONSTRAINT `FKmfjtb1i3scyfw0htyctcfn75i` FOREIGN KEY (`lbox_region_id`) REFERENCES `lbox_region` (`lbox_region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_game` (
  `lbox_game_id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `release_date` varchar(32) DEFAULT NULL,
  `release_year` int(11) DEFAULT NULL,
  `developer_id` bigint(20) DEFAULT NULL,
  `lbox_platform_id` bigint(20) NOT NULL,
  `publisher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`lbox_game_id`),
  KEY `FK47xbyy117vi0crl6qdhfnprmt` (`developer_id`),
  KEY `FKosfwe1ui84vkwr7g7e2ypjf5o` (`publisher_id`),
  KEY `FK6wwyt8a9mpp8k7hpfpq2w473j` (`lbox_platform_id`),
  CONSTRAINT `FK47xbyy117vi0crl6qdhfnprmt` FOREIGN KEY (`developer_id`) REFERENCES `lbox_company` (`lbox_company_id`),
  CONSTRAINT `FK6wwyt8a9mpp8k7hpfpq2w473j` FOREIGN KEY (`lbox_platform_id`) REFERENCES `lbox_platform` (`lbox_platform_id`),
  CONSTRAINT `FKosfwe1ui84vkwr7g7e2ypjf5o` FOREIGN KEY (`publisher_id`) REFERENCES `lbox_company` (`lbox_company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_datafile_game` (
  `datafile_game_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbox_datafile_id` bigint(20) NOT NULL,
  `lbox_game_id` varchar(32) NOT NULL,
  PRIMARY KEY (`datafile_game_id`),
  UNIQUE KEY `UKrh0jgw1bxnpnkb2m2fssyyxlq` (`lbox_datafile_id`,`lbox_game_id`),
  KEY `FKg9rayoxfwobyx05xlvcpyayxt` (`lbox_game_id`),
  CONSTRAINT `FKcw3mobv79fcfjeobvom6vx66a` FOREIGN KEY (`lbox_datafile_id`) REFERENCES `lbox_datafile` (`lbox_datafile_id`),
  CONSTRAINT `FKg9rayoxfwobyx05xlvcpyayxt` FOREIGN KEY (`lbox_game_id`) REFERENCES `lbox_game` (`lbox_game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_game_file_name` (
  `lbox_game_id` varchar(32) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  PRIMARY KEY (`lbox_game_id`,`file_name`),
  CONSTRAINT `FKios60isp55knr6k6icr2jly79` FOREIGN KEY (`lbox_game_id`) REFERENCES `lbox_game` (`lbox_game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_game_genre` (
  `game_genre_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbox_game_id` varchar(32) NOT NULL,
  `lbox_genre_id` bigint(20) NOT NULL,
  PRIMARY KEY (`game_genre_id`),
  UNIQUE KEY `UK7jm9oxkot0ri6ju0opmnqrfm5` (`lbox_game_id`,`lbox_genre_id`),
  KEY `FKjxjoynlocljavwbo1n8jgrtyw` (`lbox_genre_id`),
  CONSTRAINT `FK47qwdwkq8xl8q3evns14ds4hq` FOREIGN KEY (`lbox_game_id`) REFERENCES `lbox_game` (`lbox_game_id`),
  CONSTRAINT `FKjxjoynlocljavwbo1n8jgrtyw` FOREIGN KEY (`lbox_genre_id`) REFERENCES `lbox_genre` (`lbox_genre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_game_image` (
  `game_image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crc32` varchar(10) DEFAULT NULL,
  `file_name` varchar(255) NOT NULL,
  `image_type` varchar(64) NOT NULL,
  `lbox_game_id` varchar(32) NOT NULL,
  `lbox_region_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`game_image_id`),
  UNIQUE KEY `UKg7x59hk3ljp3ftkyh4leapd1t` (`lbox_game_id`,`image_type`,`file_name`,`crc32`,`lbox_region_id`),
  KEY `FKol9r11w1sytdc01seawkrr3sm` (`lbox_region_id`),
  CONSTRAINT `FK9wf0vdjwsglnww4ko2oru3bss` FOREIGN KEY (`lbox_game_id`) REFERENCES `lbox_game` (`lbox_game_id`),
  CONSTRAINT `FKol9r11w1sytdc01seawkrr3sm` FOREIGN KEY (`lbox_region_id`) REFERENCES `lbox_region` (`lbox_region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `lbox_game_region` (
  `game_region_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lbox_game_id` varchar(32) NOT NULL,
  `lbox_region_id` bigint(20) NOT NULL,
  PRIMARY KEY (`game_region_id`),
  UNIQUE KEY `UK622ulsrqkmx05crepcb08tj5r` (`lbox_game_id`,`lbox_region_id`),
  KEY `FKpa147bb52tqn7ktxvpnxymfo3` (`lbox_region_id`),
  CONSTRAINT `FK2qpmfkv4d78m8hko6v1l8d5vf` FOREIGN KEY (`lbox_game_id`) REFERENCES `lbox_game` (`lbox_game_id`),
  CONSTRAINT `FKpa147bb52tqn7ktxvpnxymfo3` FOREIGN KEY (`lbox_region_id`) REFERENCES `lbox_region` (`lbox_region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
