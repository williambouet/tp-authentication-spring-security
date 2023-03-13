-- Active: 1678202974500@@127.0.0.1@3306

CREATE DATABASE
    spring_security_demo DEFAULT CHARACTER SET = 'utf8mb4';

USE spring_security_demo;
-- Active: 1678202974500@@127.0.0.1@3306@USER

CREATE USER 'springsercurityadmin'@'localhost' IDENTIFIED BY 'WCS$@2023';

-- Grant select privilege to all databases;


GRANT ALL PRIVILEGES ON spring_security_demo.* TO 'springsercurityadmin'@'localhost' WITH GRANT OPTION;

FLUSH PRIVILEGES;