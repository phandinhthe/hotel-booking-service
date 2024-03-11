create table if not exists location (
  `id` varchar(36),
  `name` varchar,
  `longitude` decimal(11, 8),
  `latitude` decimal(10, 8),
  `country` varchar(60) not null,
  `city` varchar(60) not null,
  `state` varchar(13),

  primary key (id)
);

create table if not exists `hotel` (
  `id` varchar(36),
  `name` varchar(60) not null,
  `location_id` varchar(36) not null,
  `phone_number` varchar not null,
  `created_date` datetime,
  `last_updated_date` datetime,

  primary key (id),
  foreign key (location_id) references `location` (id)
);

create table if not exists `room` (
  `id` varchar(36),
  `name` varchar,
  `hotel_id` varchar(36),
  `type` varchar not null,
  `bed_type` varchar not null,
  `area` varchar not null,
  `price` decimal(10, 2),
  `detail` varchar,
  `created_date` datetime,
  `last_updated_date` datetime,

  primary key (id),
  foreign key (hotel_id) references `hotel` (id)
);

create table if not exists `customer` (
  `id` varchar(36),
  `first_name` varchar not null,
  `last_name` varchar not null,
  `middle_name` varchar not null,
  `phone_number` varchar not null,
  `address` varchar not null,

  `created_date` datetime,
  `last_updated_date` datetime,

  primary key (id)
);

create table if not exists `booking` (
  `id` varchar(36),
  `status` varchar(30) not null,
  `room_id` varchar(36) not null,
  `customer_id` varchar(36) not null,

  `check_in_date` datetime not null,
  `check_out_date` datetime not null,

  `created_date` datetime,
  `last_updated_date` datetime,

  primary key (id),
  foreign key (room_id) references `room` (id),
  foreign key (customer_id) references `customer` (id)
);
