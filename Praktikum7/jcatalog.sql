-- phpMyAdmin SQL Dump
-- version 2.9.0.1
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Jan 17, 2007 at 02:57 PM
-- Server version: 5.0.24
-- PHP Version: 5.1.6
-- 
-- Database: `catalog`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `category`
-- 

CREATE TABLE `category` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(50) collate latin1_general_ci NOT NULL,
  `description` varchar(255) collate latin1_general_ci default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=5 ;

-- 
-- Dumping data for table `category`
-- 

INSERT INTO `category` (`id`, `name`, `description`) VALUES (1, 'Category One', 'Category one description');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (2, 'Category Two', 'Category two  description');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (3, 'Category Three', 'Category three description');
INSERT INTO `category` (`id`, `name`, `description`) VALUES (4, 'Category Four', 'Category four description');

-- --------------------------------------------------------

-- 
-- Table structure for table `product`
-- 

CREATE TABLE `product` (
  `id` int(10) NOT NULL auto_increment,
  `name` varchar(50) collate latin1_general_ci NOT NULL,
  `price` decimal(8,2) default NULL,
  `width` decimal(8,2) default NULL,
  `height` decimal(8,2) default NULL,
  `description` longtext collate latin1_general_ci,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci AUTO_INCREMENT=9 ;

-- 
-- Dumping data for table `product`
-- 

INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (1, 'Product1', 20.00, 12.00, 16.00, 'JavaServer Faces (JSF) technology is a new user interface framework for J2EE applications. It is particularly suited, by design, for use with applications based on the MVC architecture. Numerous articles have been published to introduce JSF. However, most of them take a highly theoretical approach that does not meet the challenges of real-world enterprise development. There are still a lot of issues that need to be solved. For example, how JSF fits INTO the overall MVC architecture? How JSF integrates with other Java frameworks? Should business logic exist in the JSF backing beans? How to handle security in JSF? And most importantly, how to build a real-world web application using JSF? This article addresses all these issues. It shows you how to integrate JSF with other Java frameworks – specifically, Spring Framework and Hibernate.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (2, 'Product2', 40.00, 18.00, 16.00, 'The sample application used in this article is an online product catalog system. By showing you how to build a real-world web application, this article covers each phase of the web application design, including business requirement gathering, analysis, technology selection, high-level architecture and implementation level design. It discusses the advantages and disadvantages of the technologies used in the sample application. It also demonstrates the approach to designing some key aspects of the sample application.This article is aimed at Java architects, developers already working with J2EE based web application. It is not an introduction to JSF, Spring Framework and Hibernate.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (3, 'Product3', 38.88, 20.00, 18.00, 'The sample application is a real-world web application, which is realistic enough to provide the basis for a meaningful discussion of web application architectural decisions. It is important to begin by presenting the requirements of the sample application. I will refer back to this section throughout the rest of the article to address the technical decisions and architecture design. The first phase in designing a web application is to gather functional requirements for the system. The sample application is a typical e-business application system.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (4, 'Product4', 22.99, 18.00, 14.00, 'There are two groups of pages in the sample application – public internet and administration intranet. The intranet is only accessible to the users who log in the system successfully. ProductSummary is not presented to the users as a separate page. It is shown inside a HTML frame within the Catalog page. ProductList is a special catalog viewable only by the administrators. It contains links to create product, edit product and delete product.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (5, 'Product5', 18.68, 17.00, 21.00, 'A multi-tier architecture partitions the whole system INTO distinct functional units - client, presentation, business logic, integration and enterprise information system (EIS). This ensures a clean division of responsibility and makes the system more maintainable and extensible. Systems with three or more tiers have proven more scalable and flexible than a client-server system, in which there is no business logic middle tier.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (6, 'Product6', 28.99, 19.00, 20.00, 'The business logic tier contains the business objects and the business services of an application. It receives requests from the presentation tier, processes the business logic based on the requests and mediates access to EIS tier resources. Business logic tier components benefit most from system level services such as security management, transaction management and resource management.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (7, 'Product7', 36.66, 16.00, 18.00, 'A multi-tier non-distributed architecture is used for the sample application. The diagram shows us the partitioning of the application tiers, and the technologies chosen for each tier. It also serves as a deployment diagram of the sample application. For a collocated architecture, the presentation tier, business logic tier and integration tier are located in the same web container physically. Well-defined interfaces are used to isolate the responsibility for each tier. The collocated architecture makes the application simple and scalable.');
INSERT INTO `product` (`id`, `name`, `price`, `width`, `height`, `description`) VALUES (8, 'Product8', 32.99, 26.00, 18.00, 'Model-View-Controller (MVC) is the Java BluePrints recommended architectural design pattern for interactive application. MVC separates design concerns, decreasing code duplication, centralizing control, and making the application more extensible. MVC also helps developers with different skill sets to focus on their core skills and collaborate through clearly defined interfaces. MVC is the architectural design pattern for the presentation tier.');

-- --------------------------------------------------------

-- 
-- Table structure for table `product_category`
-- 

CREATE TABLE `product_category` (
  `product_id` int(10) NOT NULL,
  `category_id` int(10) NOT NULL,
  PRIMARY KEY  (`product_id`,`category_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Dumping data for table `product_category`
-- 

INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (1, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (2, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (3, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (4, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (5, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (6, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (7, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (8, 1);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (1, 2);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (4, 2);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (6, 2);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (8, 2);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (1, 3);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (2, 3);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (7, 3);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (1, 4);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (3, 4);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (5, 4);
INSERT INTO `product_category` (`product_id`, `category_id`) VALUES (8, 4);

-- --------------------------------------------------------

-- 
-- Table structure for table `user`
-- 

CREATE TABLE `user` (
  `username` varchar(20) collate latin1_general_ci NOT NULL,
  `password` varchar(32) collate latin1_general_ci NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

-- 
-- Dumping data for table `user`
-- 

INSERT INTO `user` (`username`, `password`) VALUES ('admin', 'abe6db4c9f5484fae8d79f2e868a673c');

-- 
-- Constraints for dumped tables
-- 

-- 
-- Constraints for table `product_category`
-- 
ALTER TABLE `product_category`
  ADD CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),  ADD CONSTRAINT `product_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
