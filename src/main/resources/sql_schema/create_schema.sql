-- ITINARYS INITIAL DATABASE CREATION
-- VERSION 1.0

DROP DATABASE IF EXISTS itineraire;

CREATE DATABASE itineraire DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE TABLE itineraire.trajet( id INT PRIMARY KEY NOT NULL, arret VARCHAR(100), prochain_arret VARCHAR(100));

Insert into itineraire.trajet values (1, "Pikine", "Guediawaye");
Insert into itineraire.trajet values (2, "Guediawaye", "Golf");
Insert into itineraire.trajet values (3, "Golf", "Colobane");

COMMIT;