CREATE TABLE `mame` (
  `mame_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `build` varchar(16) NOT NULL,
  `debug` varchar(3) DEFAULT NULL,
  `mameconfig` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`mame_id`),
  UNIQUE KEY `MAME_0001` (`build`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_machine` (
  `mame_machine_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cloneof` varchar(16) DEFAULT NULL,
  `description` varchar(160) DEFAULT NULL,
  `isbios` varchar(3) DEFAULT NULL,
  `isdevice` varchar(3) DEFAULT NULL,
  `ismechanical` varchar(3) DEFAULT NULL,
  `manufacturer` varchar(80) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `romof` varchar(16) DEFAULT NULL,
  `runnable` varchar(3) DEFAULT NULL,
  `sampleof` varchar(32) DEFAULT NULL,
  `sourcefile` varchar(48) NOT NULL,
  `year` varchar(8) DEFAULT NULL,
  `mame_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_machine_id`),
  UNIQUE KEY `MAME_MACHINE_0001` (`mame_id`,`name`),
  CONSTRAINT `FKpqjejppegsdypo9c5ym7qcpor` FOREIGN KEY (`mame_id`) REFERENCES `mame` (`mame_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_adjuster` (
  `mame_adjuster_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `default_value` int(11) DEFAULT NULL,
  `name` varchar(32) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_adjuster_id`),
  UNIQUE KEY `MAME_ADJUSTER_0001` (`machine_id`,`name`),
  CONSTRAINT `FK8blnyu0alpr9lgnv7gv6scuc7` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_biosset` (
  `mame_biosset_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(3) DEFAULT NULL,
  `description` varchar(96) NOT NULL,
  `name` varchar(32) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_biosset_id`),
  UNIQUE KEY `MAME_BIOSSET_0001` (`machine_id`,`name`),
  KEY `MAME_BIOSSET_0002` (`name`),
  CONSTRAINT `FK9iycx0ngo0tbjcxr42wvumcvg` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_chip` (
  `mame_chip_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clock` varchar(16) DEFAULT NULL,
  `name` varchar(48) NOT NULL,
  `tag` varchar(128) NOT NULL,
  `type` varchar(5) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_chip_id`),
  UNIQUE KEY `MAME_CHIP_0001` (`machine_id`,`tag`,`type`,`name`),
  KEY `MAME_CHIP_0002` (`name`,`machine_id`),
  CONSTRAINT `FKr5fd5cln9ms2tajyr853uwgtf` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_configuration` (
  `mame_configuration_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mask` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `tag` varchar(128) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_configuration_id`),
  UNIQUE KEY `MAME_CONFIGURATION_0001` (`machine_id`,`name`,`mask`,`tag`),
  CONSTRAINT `FK411ymoq0il248p1745bb2964g` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_confsetting` (
  `mame_confsetting_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(255) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `value` int(11) NOT NULL,
  `configuration_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_confsetting_id`),
  UNIQUE KEY `MAME_CONFSETTING_0001` (`configuration_id`,`name`,`value`),
  CONSTRAINT `FKslonohd0o9ttxgvqhuudibrik` FOREIGN KEY (`configuration_id`) REFERENCES `mame_configuration` (`mame_configuration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `mame_device` (
  `mame_device_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interface` varchar(20) DEFAULT NULL,
  `fixed_image` int(11) DEFAULT NULL,
  `mandatory` int(11) DEFAULT NULL,
  `tag` varchar(128) NOT NULL,
  `type` varchar(16) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_device_id`),
  UNIQUE KEY `MAME_DEVICE_0001` (`machine_id`,`type`,`tag`),
  CONSTRAINT `FKoeonqugthgd0fh53glosytpde` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_deviceextension` (
  `mame_deviceextension_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(8) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_deviceextension_id`),
  UNIQUE KEY `MAME_DEVICEEXTENSION_0001` (`device_id`,`name`),
  CONSTRAINT `FKcertpr97dcq5j7mky8jsqw4jk` FOREIGN KEY (`device_id`) REFERENCES `mame_device` (`mame_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_deviceinstance` (
  `mame_deviceinstance_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `briefname` varchar(8) DEFAULT NULL,
  `name` varchar(16) NOT NULL,
  `device_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_deviceinstance_id`),
  UNIQUE KEY `MAME_DEVICEINSTANCE_0001` (`device_id`,`name`),
  CONSTRAINT `FKdc20mn4qyg6v6oj3ascc95859` FOREIGN KEY (`device_id`) REFERENCES `mame_device` (`mame_device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_deviceref` (
  `mame_deviceref_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_deviceref_id`),
  KEY `MAME_DEVICEREF_0001` (`machine_id`,`name`),
  CONSTRAINT `FKj6299lyj6v415crcgval18ucq` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_dipswitch` (
  `mame_dipswitch_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mask` bigint(20) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `tag` varchar(128) DEFAULT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_dipswitch_id`),
  KEY `MAME_DIPSWITCH_0001` (`machine_id`,`name`,`mask`,`tag`),
  CONSTRAINT `FKexnwi2y2f0u9tw6uap0rqkrdo` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_dipvalue` (
  `mame_dipvalue_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(3) DEFAULT NULL,
  `name` varchar(96) NOT NULL,
  `value` bigint(20) NOT NULL,
  `dipswitch_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_dipvalue_id`),
  KEY `MAME_DIPVALUE_0001` (`dipswitch_id`,`name`,`value`,`isdefault`),
  CONSTRAINT `FKqn89oocdpvvfgxy2a4v33x3i7` FOREIGN KEY (`dipswitch_id`) REFERENCES `mame_dipswitch` (`mame_dipswitch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_disk` (
  `mame_disk_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disk_index` int(11) DEFAULT NULL,
  `merge` varchar(64) DEFAULT NULL,
  `name` varchar(64) NOT NULL,
  `optional` varchar(3) DEFAULT NULL,
  `region` varchar(32) DEFAULT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `writable` varchar(3) DEFAULT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_disk_id`),
  UNIQUE KEY `MAME_DISK_0001` (`machine_id`,`name`),
  CONSTRAINT `FKprgxoxocl8vxyf1i8xp3f7v9f` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_display` (
  `mame_display_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `flipx` varchar(3) DEFAULT NULL,
  `hbend` int(11) DEFAULT NULL,
  `hbstart` int(11) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `htotal` int(11) DEFAULT NULL,
  `pixclock` int(11) DEFAULT NULL,
  `refresh` varchar(16) DEFAULT NULL,
  `rotate` varchar(3) DEFAULT NULL,
  `tag` varchar(128) NOT NULL,
  `type` varchar(8) NOT NULL,
  `vbend` int(11) DEFAULT NULL,
  `vbstart` int(11) DEFAULT NULL,
  `vtotal` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_display_id`),
  UNIQUE KEY `MAME_DISPLAY_0001` (`machine_id`,`tag`,`type`),
  CONSTRAINT `FKs7momd0n0sgc25xbd0j462ibj` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_driver` (
  `mame_driver_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cocktail` varchar(12) DEFAULT NULL,
  `color` varchar(12) DEFAULT NULL,
  `emulation` varchar(12) NOT NULL,
  `graphic` varchar(12) DEFAULT NULL,
  `protection` varchar(12) DEFAULT NULL,
  `savestate` varchar(12) DEFAULT NULL,
  `sound` varchar(12) DEFAULT NULL,
  `status` varchar(12) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_driver_id`),
  UNIQUE KEY `UK_8yhqs7r8ku5ad99vw546wacyu` (`machine_id`),
  CONSTRAINT `FK6pey10mhc79rl70n9ruu3y61u` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_input` (
  `mame_input_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coins` int(11) DEFAULT NULL,
  `players` int(11) NOT NULL,
  `service` varchar(3) DEFAULT NULL,
  `tilt` varchar(3) DEFAULT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_input_id`),
  UNIQUE KEY `UK_7bjo6y8sdy0lmh1381antser1` (`machine_id`),
  CONSTRAINT `FK5oc5c9pvel1i3vh3mwv388h8u` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_inputcontrol` (
  `mame_inputcontrol_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buttons` int(11) DEFAULT NULL,
  `keydelta` int(11) DEFAULT NULL,
  `maximum` int(11) DEFAULT NULL,
  `minimum` int(11) DEFAULT NULL,
  `player` int(11) DEFAULT NULL,
  `reqbuttons` int(11) DEFAULT NULL,
  `reverse` varchar(3) DEFAULT NULL,
  `sensitivity` int(11) DEFAULT NULL,
  `type` varchar(16) NOT NULL,
  `ways` varchar(16) DEFAULT NULL,
  `ways2` varchar(16) DEFAULT NULL,
  `ways3` varchar(16) DEFAULT NULL,
  `input_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_inputcontrol_id`),
  UNIQUE KEY `MAME_INPUTCONTROL_0001` (`input_id`,`type`,`player`),
  CONSTRAINT `FK50btxa4koriggn5ojusiqat2l` FOREIGN KEY (`input_id`) REFERENCES `mame_input` (`mame_input_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_port` (
  `mame_port_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag` varchar(128) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_port_id`),
  KEY `MAME_PORT_0001` (`machine_id`,`tag`),
  CONSTRAINT `FKstmluhi5r81a6qrmwnqodoo3v` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_analog` (
  `mame_analog_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mask` int(11) NOT NULL,
  `port_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_analog_id`),
  KEY `MAME_ANALOG_0001` (`port_id`,`mask`),
  CONSTRAINT `FKmh6ouolm2705omw20bnm88tja` FOREIGN KEY (`port_id`) REFERENCES `mame_port` (`mame_port_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_ramoption` (
  `mame_ramoption_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` int(11) DEFAULT NULL,
  `content` bigint(20) DEFAULT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_ramoption_id`),
  UNIQUE KEY `MAME_RAMOPTION_0001` (`machine_id`,`content`,`isdefault`),
  CONSTRAINT `FKprgyf61unkgpxgq5hbnlr22wn` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_rom` (
  `mame_rom_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bios` varchar(32) DEFAULT NULL,
  `crc` varchar(8) DEFAULT NULL,
  `merge` varchar(160) DEFAULT NULL,
  `name` varchar(160) NOT NULL,
  `offset` varchar(8) DEFAULT NULL,
  `optional` varchar(3) DEFAULT NULL,
  `region` varchar(40) DEFAULT NULL,
  `sha1` varchar(40) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_rom_id`),
  KEY `MAME_ROM_0001` (`machine_id`,`name`,`bios`,`region`,`offset`),
  CONSTRAINT `FK2xil8p2yqlivhr1bet3m7au2f` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_sample` (
  `mame_sample_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_sample_id`),
  KEY `MAME_SAMPLE_0001` (`machine_id`,`name`),
  CONSTRAINT `FK2gnb7a33xliss6b0eyqbt90g1` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_slot` (
  `mame_slot_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_slot_id`),
  UNIQUE KEY `MAME_SLOT_0001` (`machine_id`,`name`),
  CONSTRAINT `FKsdmy8iam7n0k0ruxnkbp9xsf1` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_slotoption` (
  `mame_slotoption_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdefault` varchar(3) DEFAULT NULL,
  `devname` varchar(32) NOT NULL,
  `name` varchar(16) NOT NULL,
  `slot_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_slotoption_id`),
  UNIQUE KEY `MAME_SLOTOPTION_0001` (`slot_id`,`name`),
  CONSTRAINT `FK3ugq9p7tqcfair99gqf8dwtus` FOREIGN KEY (`slot_id`) REFERENCES `mame_slot` (`mame_slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_softwarelist` (
  `mame_softwarelist_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `filter` varchar(8) DEFAULT NULL,
  `name` varchar(16) NOT NULL,
  `status` varchar(10) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_softwarelist_id`),
  UNIQUE KEY `MAME_SOFTWARELIST_0001` (`machine_id`,`name`),
  CONSTRAINT `FK13yebaayffsvqcneanslmishs` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mame_sound` (
  `mame_sound_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channels` int(11) NOT NULL,
  `machine_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mame_sound_id`),
  UNIQUE KEY `UK_5uca6vwmhykq0enxgwk932abg` (`machine_id`),
  CONSTRAINT `FK8lh2yudx42w6shj4g3yihxupo` FOREIGN KEY (`machine_id`) REFERENCES `mame_machine` (`mame_machine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
