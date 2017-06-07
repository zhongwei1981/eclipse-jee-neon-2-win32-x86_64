CREATE TABLE "user" (
  id int NOT NULL IDENTITY,
  name varchar(24) NOT NULL PRIMARY KEY,
  email varchar(32) DEFAULT NULL
)