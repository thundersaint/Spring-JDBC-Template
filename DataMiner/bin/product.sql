CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(75) NOT NULL,
  `description` blob NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `product_url` blob NOT NULL,
  `image_url` blob NOT NULL,
  `product_id` varchar(50) NOT NULL,
  `click_bid` decimal(10,0) DEFAULT NULL,
  `promotion` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;