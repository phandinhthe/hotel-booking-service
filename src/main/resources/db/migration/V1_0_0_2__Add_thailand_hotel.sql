-- location id: 018e244b-6041-7ef2-beca-889ba4c89166
-- hotel id   : 018e244b-6041-7ff1-a679-0e24386498e0
-- room  id   : 018e244b-6041-7a50-a4a5-9f34b07df873
insert into `location` values (
  '018e244b-6041-7ef2-beca-889ba4c89166',
  '1000 Sukhumvit, Asoke district',
  106.6975118,
  10.77022632,
  'Thailand',
  'Bangkok',
  ''
);

insert into `hotel` values (
  '018e244b-6041-7ff1-a679-0e24386498e0',
  'hilton thailand',
  '018e244b-6041-7ef2-beca-889ba4c89166',
  '+66 000 000',
  '2024-04-03 00:00:00',
  '2024-04-03 00:00:00'
);

insert into `room` values (
  '018e244b-6041-7a50-a4a5-9f34b07df873',
  'deluxe king - sea view',
  '018e244b-6041-7ff1-a679-0e24386498e0',
  'deluxe king room',
  '1 king bed',
  '35m',
  300.33,
  'high floor, nice view',
  '2024-04-03 00:00:00',
  '2024-04-03 00:00:00'
);

-- location id: 018e2452-117b-78db-9da7-d89efe9eb180
-- hotel id   : 018e2452-117b-7658-826c-176c4564e22a
-- room  id   : 018e2452-117b-7704-87f9-00d7c414a979
insert into `location` values (
  '018e2452-117b-78db-9da7-d89efe9eb180',
  '10 Sukhumvit Road ',
  106.6975118,
  10.77022632,
  'Thailand',
  'Bangkok',
  ''
);

insert into `hotel` values (
  '018e2452-117b-7658-826c-176c4564e22a',
  'Pullman Bangkok',
  '018e2452-117b-78db-9da7-d89efe9eb180',
  '+60 000 000',
  '2024-05-03 00:00:00',
  '2024-05-03 00:00:00'
);

insert into `room` values (
  '018e2452-117b-7704-87f9-00d7c414a979',
  'Premium king',
  '018e2452-117b-7658-826c-176c4564e22a',
  'deluxe king room',
  '1 king bed',
  '55m',
  344.44,
  'high floor, nice view',
  '2024-05-03 00:00:00',
  '2024-05-03 00:00:00'
);
