USE movieapp;

-- Populating movie table
INSERT INTO movie 
VALUES	(1, 'Tenet'), 
		(2, 'Guardians of the Galaxy'),
        (3, 'Knives Out'),
        (4, 'Parasite');       

-- Populating screen table
INSERT INTO screen
VALUES	(1, 20), 
		(2, 20),
        (3, 20),
        (4, 20);
        
-- Populating showtime table         
INSERT INTO showtime 
VALUES	(1001, 1, "5/12/20", "9:00", 1),
		(1002, 2, "5/12/20", "12:00", 2),
        (1003, 3, "5/12/20", "13:00", 3),
        (1004, 4, "5/12/20", "13:00", 4),
        (1005, 1, "5/12/20", "16:00", 1),
        (1006, 2, "5/12/20", "16:40", 2),
        (1007, 3, "5/12/20", "16:00", 3),
        (1008, 4, "5/12/20", "16:30", 4),
        (1009, 1, "6/12/20", "9:00", 1),
		(1010, 2, "6/12/20", "12:00", 2),
        (1011, 3, "6/12/20", "13:00", 3),
        (1012, 4, "6/12/20", "13:00", 4),
        (1013, 1, "6/12/20", "16:00", 1),
        (1014, 2, "6/12/20", "16:40", 2),
        (1015, 3, "6/12/20", "16:00", 3),
        (1016, 4, "6/12/20", "16:30", 4),
		(1017, 1, "7/12/20", "9:00", 1),
		(1018, 2, "7/12/20", "12:00", 2),
        (1019, 3, "7/12/20", "13:00", 3),
        (1020, 4, "7/12/20", "13:00", 4),
        (1021, 1, "7/12/20", "16:00", 1),
        (1022, 2, "7/12/20", "16:40", 2),
        (1023, 3, "7/12/20", "16:00", 3),
        (1024, 4, "7/12/20", "16:30", 4);

-- Seats populated in java

-- Populating ruser table
INSERT INTO ruser 
VALUES	(1, "John", "Smith", "123 Calgary Ave", "jsmith@fake.com", "pass123", 123456, "1/1/21"),
	    (2, "Jane", "Doe", "321 Calgary Ave", "jdoe@fake.com", "pass123", 848429, "1/1/20"),
        (3, "Jack", "Roberts", "123 Edmonton Ave", "jrob@fake.com", "pass123", 959983, "1/5/24"),
        (4, "Sally", "Vasquez", "321 Edmonton Ave", "svasq@fake.com", "pass123", 834735, "1/6/21"),
        (5, "Rhea", "Witt", "123 Vancouver Ave", "rwitt@fake.com", "pass123", 187541, "1/5/21"),
        (6, "Rhona", "Brock", "321 Vancouver Ave", "rbrock@fake.com", "pass123", 766591, "1/7/21"),
        (7, "Tyla", "Olson", "123 Toronto Ave", "tolson@fake.com", "pass123", 828803, "1/8/21"),
        (8, "Lexi", "Miller", "321 Toronto Ave", "jsmith@fake.com", "pass123", 724329, "1/9/21");

-- Populating some vouchers
INSERT INTO voucher
VALUES (1, "testemail@fake.com"),
	   (1, "vouchertest@fake.com");

