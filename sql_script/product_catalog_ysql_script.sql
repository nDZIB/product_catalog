CREATE DATABASE productCatalog;
USE productCatalog;

CREATE TABLE category(
	categoryID int AUTO_INCREMENT,
	categoryName varchar(20),
	categoryDescription varchar (100),
	PRIMARY KEY(categoryID)
);

CREATE TABLE product (
	categoryID int NOT NULL,
	productID int AUTO_INCREMENT,
	productName varchar(20),
	productDescription varchar(100),
	productColor varchar(10),
	
	PRIMARY KEY(productID),
	FOREIGN KEY (categoryID) REFERENCES category(categoryID)
);

CREATE TABLE systemUser (
	userID integer AUTO_INCREMENT,
	userRealName varchar(30) NOT NULL,
	userName varchar(10),
	userPassword char(60) NOT NULL,
	PRIMARY KEY(userID)
);