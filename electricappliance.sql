DROP DATABASE electricappliance; 
CREATE DATABASE electricappliance;
USE electricappliance;
DROP TABLE house_appliances;
CREATE TABLE house_appliances(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(20),
power INT,
isPowered BOOLEAN
);

INSERT INTO house_appliances (name,power,isPowered) VALUES ('a',200,true);
SELECT * FROM house_appliances;
