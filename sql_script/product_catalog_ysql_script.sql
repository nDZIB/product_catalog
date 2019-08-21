CREATE DATABASE productCatalog;

CREATE TABLE `category` (
  `categoryID` int(11) NOT NULL,
  `categoryName` varchar(20) DEFAULT NULL,
  `categoryDescription` varchar(100) DEFAULT NULL
);

CREATE TABLE `product` (
  `categoryID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `productName` varchar(20) DEFAULT NULL,
  `productDescription` varchar(100) DEFAULT NULL,
  `productColor` varchar(10) DEFAULT NULL
) ;

INSERT INTO `category` (`categoryID`, `categoryName`, `categoryDescription`) VALUES
(1, 'Kitchen', 'Kitchen utensils'),
(2, 'Agriculture', 'Farming tools'),
(13, 'drink', 'Kitchen utensils');
INSERT INTO `product` (`categoryID`, `productID`, `productName`, `productDescription`, `productColor`) VALUES
(1, 2, 'Pan', 'shallow plate-like vessel for frying food-stuff', 'Black'),
(13, 7, 'planet orange', 'shallow plate-like vessel for frying food-stuff', 'Green');
CREATE TABLE `systemuser` (
  `userID` int(11) NOT NULL,
  `userRealName` varchar(30) NOT NULL,
  `userName` varchar(10) DEFAULT NULL,
  `userPassword` char(60) NOT NULL
) ;
INSERT INTO `systemuser` (`userID`, `userRealName`, `userName`, `userPassword`) VALUES
(1, 'Bruno Nzi', 'ndzib', 'bruno'),
(2, 'Serkwi Bruno Ndzi', 'ndzib', 'ndzib');
ALTER TABLE `category`
  ADD PRIMARY KEY (`categoryID`);
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`),
  ADD KEY `categoryID` (`categoryID`);
ALTER TABLE `systemuser`
  ADD PRIMARY KEY (`userID`);
ALTER TABLE `category`
  MODIFY `categoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
ALTER TABLE `systemuser`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`) ON DELETE CASCADE;

