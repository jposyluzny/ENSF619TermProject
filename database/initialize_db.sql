DROP DATABASE IF EXISTS movieapp;
CREATE DATABASE movieapp; 
USE movieapp;

DROP TABLE IF EXISTS movie;
CREATE TABLE movie (
  movieid  		  integer not null,
  name      	  varchar(100) not null,
  unique (movieid),
  primary key (movieid)
);

DROP TABLE IF EXISTS screen;
CREATE TABLE screen (
  screenid 		  integer not null,
  numseats        integer not null,
  unique (screenid),
  primary key (screenid)
);

DROP TABLE IF EXISTS showtime;
CREATE TABLE showtime (
  showid  	  integer not null,
  movieid     integer not null, 
  date		  varchar(12) not null,
  time		  varchar(12) not null,
  screen      integer not null,
  unique(showid),
  primary key (showid),
  foreign key (movieid) references movie(movieid),
  foreign key (screen) references screen(screenid)
);

DROP TABLE IF EXISTS seat;
CREATE TABLE seat (
  showid      integer not null,
  number      integer not null,
  isoccupied  boolean not null,
  foreign key (showid) references showtime(showid),
  primary key (showid, number)
);

DROP TABLE IF EXISTS ruser;
CREATE TABLE ruser (
  userid      integer not null,
  fname		  varchar(30) not null,
  lname		  varchar(30) not null,
  address	  varchar(100) not null,
  email		  varchar(100) not null,
  password    varchar(100) not null,
  ccinfo	  integer not null,
  expiry	  varchar(12) not null,
  unique (userid),
  primary key (userid)
);

DROP TABLE IF EXISTS ticket;
CREATE TABLE ticket (
  ticketid     integer not null,
  showtime     integer not null,
  movie		   integer not null,
  user		   integer,
  email		   varchar(100) not null,
  unique (ticketid),
  primary key (ticketid),
  foreign key (showtime) references showtime(showid),
  foreign key (movie) references movie(movieid)
);
