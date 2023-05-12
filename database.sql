CREATE TABLE `product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `PRICE` float NOT NULL,
  `TYPE` varchar(25) DEFAULT NULL,
  `MFG_DATE` date DEFAULT NULL,
  `EXP_DATE` date DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1


CREATE TABLE `order_product` (
  `productID` int(11) NOT NULL,
  `orderID` int(11) NOT NULL,
  PRIMARY KEY (`productID`,`orderID`),
  KEY `orderID` (`orderID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1


	CREATE TABLE `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `items_count` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1


