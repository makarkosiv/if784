CREATE SCHEMA if not exist "demo";
use "demo";
create table "demo"."Employee" ( "id" int NOT NULL AUTO_INCREMENT,
    "name" varchar(255) NOT NULL,
    "salary" varchar(255) not null,
    PRIMARY KEY ("id") )
    ENGINE = InnoDB;