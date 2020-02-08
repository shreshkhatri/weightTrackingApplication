-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2018 at 07:20 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adidatabase`
--
CREATE DATABASE IF NOT EXISTS `adidatabase` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `adidatabase`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `process_dailyrecord` ()  BEGIN
	DECLARE mwt,mws,ewt,ews float;
    DECLARE avg_wt,avg_ws,net_weight float;
    DECLARE uids int ;
    DECLARE loopcontroller int DEFAULT 0;
	DECLARE tDate date;
    
    DECLARE resultset CURSOR for select dailyrecord.userID,dailyrecord.date,dailyrecord.mWeight,dailyrecord.mWaistSize,dailyrecord.eWeight,dailyrecord.eWaistSize 
	from dailyrecord WHERE dailyrecord.date!=CURRENT_DATE AND dailyrecord.status=0;
    
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET loopcontroller=1;
    open resultset;
    currentLoop: LOOP
  
	FETCH resultset INTO uids,tdate,mwt,mws,ewt,ews;
    
	IF loopcontroller=1 THEN
    	LEAVE currentLoop;
    END IF;
	
    IF (mwt=0 AND ewt=0) THEN
    SET avg_wt=0;
    SET net_weight=0;
    ELSEIF (mwt=0 OR ewt=0) THEN
    	IF mwt!=0 THEN	
        	SET avg_wt=mwt;
            SET net_weight=mwt;
        ELSEIF ewt!=0 THEN
       		SET avg_wt=ewt;
            SET net_weight=ewt;
        END IF;
  	
    ELSE
    	set avg_wt=(mwt+ewt)/2;
        SET net_weight=mwt-ewt;
    END IF;

 IF (mws=0 AND ews=0) THEN
    	SET avg_ws=0;
 ELSEIF (mws=0 OR ews=0) THEN
    	IF mws!=0 THEN	
        	SET avg_ws=mws;
        ELSEIF ews!=0 THEN
       		SET avg_ws=ews;
        END IF;
  	
  ELSE
  		SET avg_ws=(ews+mws)/2;    	
    END IF;
    
    UPDATE dailyrecord SET dailyrecord.status=1, dailyrecord.avg_weight=avg_wt,dailyrecord.avg_waistSize=avg_ws,dailyrecord.netCalorie=(dailyrecord.calorieTaken-dailyrecord.calorieBurned),dailyrecord.netWeight=net_weight WHERE dailyrecord.userID=uids AND dailyrecord.date=tdate;

	SET avg_ws=0;
    SET avg_wt=0;
    SET net_weight=0;
    END LOOP currentLoop;
    CLOSE resultset;
    END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dailyrecord`
--

CREATE TABLE `dailyrecord` (
  `date` date NOT NULL,
  `userID` int(11) NOT NULL,
  `mWeight` float NOT NULL DEFAULT '0',
  `mWaistSize` float NOT NULL DEFAULT '0',
  `eWeight` float NOT NULL DEFAULT '0',
  `eWaistSize` float NOT NULL DEFAULT '0',
  `avg_weight` float(5,2) DEFAULT NULL,
  `netWeight` float(5,2) DEFAULT NULL,
  `avg_waistSize` float NOT NULL DEFAULT '0',
  `mealAmount` float NOT NULL DEFAULT '0',
  `calorieTaken` float NOT NULL DEFAULT '0',
  `workout` float NOT NULL DEFAULT '0',
  `calorieBurned` float NOT NULL DEFAULT '0',
  `netCalorie` float(5,2) DEFAULT NULL,
  `activityLevel` varchar(25) NOT NULL DEFAULT 'inactive',
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dailyrecord`
--

INSERT INTO `dailyrecord` (`date`, `userID`, `mWeight`, `mWaistSize`, `eWeight`, `eWaistSize`, `avg_weight`, `netWeight`, `avg_waistSize`, `mealAmount`, `calorieTaken`, `workout`, `calorieBurned`, `netCalorie`, `activityLevel`, `status`) VALUES
('2017-12-23', 15, 80, 80, 75.5, 50, 77.75, 4.50, 65, 40, 800, 10, 805.5, -5.50, 'moderate', 1),
('2017-12-23', 48, 60, 60, 60.5, 30.2, 60.25, -0.50, 45.1, 0, 1588, 0, 1555, 33.00, 'moderate', 1),
('2017-12-23', 49, 50, 50, 60, 25, 55.00, -10.00, 37.5, 20, 1500, 40, 1000, 500.00, 'inactive', 1),
('2017-12-24', 15, 78, 50, 79, 51, 78.50, -1.00, 50.5, 5, 375, 0, 455, -80.00, 'inactive', 1),
('2017-12-31', 15, 90, 35, 78.5, 36, 84.25, 11.50, 35.5, 0, 858, 0, 888, -30.00, 'inactive', 1),
('2018-01-01', 15, 100, 76, 83, 20, 91.50, 17.00, 48, 0, 1001, 0, 1000, 1.00, 'moderate', 1),
('2018-01-02', 15, 80, 68, 80.8, 69, 80.40, -0.80, 68.5, 229, 15725, 105, 8213.75, 7511.25, 'moderate', 1),
('2018-01-03', 15, 70, 150, 70.6, 155, 70.30, -0.60, 152.5, 20, 2000, 50, 4302.75, -2302.75, 'moderate', 1),
('2018-01-06', 15, 80, 156, 81, 158, 80.50, -1.00, 157, 0, 0, 0, 0, 0.00, 'moderate', 1),
('2018-01-10', 15, 75, 122, 75.5, 123, 75.25, -0.50, 122.5, 0, 0, 0, 0, 0.00, 'inactive', 1),
('2018-01-14', 54, 0, 0, 0, 0, 0.00, 0.00, 0, 20, 1160, 0, 0, 999.99, 'inactive', 1);

-- --------------------------------------------------------

--
-- Table structure for table `diet`
--

CREATE TABLE `diet` (
  `dietID` int(11) NOT NULL,
  `dietName` varchar(50) NOT NULL,
  `cal_per_gram` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diet`
--

INSERT INTO `diet` (`dietID`, `dietName`, `cal_per_gram`) VALUES
(5, 'salad', 58),
(8, 'mango', 75),
(14, 'rice', 20),
(15, 'steamed rice', 40),
(25, 'pasta', 100),
(26, 'lemon', 25);

-- --------------------------------------------------------

--
-- Table structure for table `exercise`
--

CREATE TABLE `exercise` (
  `eID` int(11) NOT NULL,
  `eName` varchar(30) NOT NULL,
  `cal_per_min` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exercise`
--

INSERT INTO `exercise` (`eID`, `eName`, `cal_per_min`) VALUES
(1, 'cycling', 80.55),
(3, 'pushups', 70),
(4, 'squats', 100),
(5, 'jogging', 40),
(6, 'aerobics', 50),
(9, 'free walk', 75);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `userName` varchar(25) NOT NULL,
  `age` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `gender` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `age`, `password`, `gender`) VALUES
(15, 'shresh', 25, '33ecb55622c1a16c83fedca91183f5f4', 'male'),
(47, 'shresha', 26, 'c4ca4238a0b923820dcc509a6f75849b', 'male'),
(48, 'pratik', 25, 'c4ca4238a0b923820dcc509a6f75849b', 'male'),
(49, 'pabina', 26, '202cb962ac59075b964b07152d234b70', 'female'),
(50, 'man', 20, '39c63ddb96a31b9610cd976b896ad4f0', 'male'),
(51, 'miraj', 22, 'c4ca4238a0b923820dcc509a6f75849b', 'male'),
(52, 'sd', 455, '1aabac6d068eef6a7bad3fdf50a05cc8', 'male'),
(53, 'prasidha', 12, '03c7c0ace395d80182db07ae2c30f034', 'male'),
(54, 'newuser', 22, '698d51a19d8a121ce581499d7b701668', 'others');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dailyrecord`
--
ALTER TABLE `dailyrecord`
  ADD PRIMARY KEY (`date`,`userID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `diet`
--
ALTER TABLE `diet`
  ADD PRIMARY KEY (`dietID`),
  ADD UNIQUE KEY `dietName` (`dietName`);

--
-- Indexes for table `exercise`
--
ALTER TABLE `exercise`
  ADD PRIMARY KEY (`eID`),
  ADD UNIQUE KEY `eName` (`eName`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `userName` (`userName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diet`
--
ALTER TABLE `diet`
  MODIFY `dietID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `exercise`
--
ALTER TABLE `exercise`
  MODIFY `eID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;
DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `record_processing` ON SCHEDULE EVERY 1 DAY STARTS '2018-01-09 00:00:01' ON COMPLETION PRESERVE ENABLE DO CALL process_dailyrecord()$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
