CREATE TABLE "PUBLIC"."ADDRESS_BOOK"
(
   ID integer auto_increment PRIMARY KEY NOT NULL,
   USERNAME varchar(255) NOT NULL,
   EMAIL varchar(255) NOT NULL,
   MOBILE varchar(255) NOT NULL,
   DEPTID integer(11) NOT NULL,
   ENTRY_DATE timestamp NOT NULL
)
;
CREATE TABLE "PUBLIC"."DEPARTMENT"
(
   ID integer auto_increment PRIMARY KEY NOT NULL,
   NAME varchar(255) NOT NULL
)
;