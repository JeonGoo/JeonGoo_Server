INSERT INTO user VALUES
    (1, '중구', '신흥동 2가', 'test@test.com', 'MALE', 'bang', '{bcrypt}$2a$10$pNgP59/K191m0kXbvJC5serNc4.0HFU.YLuypUZ/l.2XrgpAOkZ3u', '1234-5678'),
    (2, '동구', '삼익아파트', 'user@user.com', 'MALE', 'myung', '{bcrypt}$2a$10$pNgP59/K191m0kXbvJC5serNc4.0HFU.YLuypUZ/l.2XrgpAOkZ3u', '1234-1234'),
    (3, '가람', '부평아파트', 'garam@garam.com', 'MALE', 'garam', '{bcrypt}$2a$10$pNgP59/K191m0kXbvJC5serNc4.0HFU.YLuypUZ/l.2XrgpAOkZ3u', '1234-1234');

INSERT INTO product VALUES
    (1, '', 'REQUEST', 'good product', 'NONE', 0, 'mac book', '500000', 'SALE', 'serial', 'USED', 1),
    (2, '', 'REQUEST', 'so product', 'NONE', 0, 'iphone', '20000', 'SALE', 'serial', 'USED', 1),
    (3, '', 'COMPLETED', 'really product', 'HIGH', 0, 'mac book pro', '1000000', 'SALE', 'serial', 'USED', 2),
    (4, 'not found serial number', 'FAILED', 'wow product', 'NONE', 0, 'window', '200000', 'SALE', 'serial', 'USED', 2),
    (5, '', 'REQUEST', 'feel good product', 'NONE', 0, 'galaxy', '20000', 'SALE', 'serial', 'USED', 3);
