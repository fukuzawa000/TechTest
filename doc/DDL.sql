-- TECHTEST.Favorites definition
CREATE TABLE `Favorites` (
  `UserId` varchar(100) NOT NULL,
  `MovieId` char(10) NOT NULL,
  PRIMARY KEY (`UserId`,`MovieId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TECHTEST.LoginToken definition
CREATE TABLE `LoginToken` (
  `UserId` varchar(100) CHARACTER SET latin1 NOT NULL,
  `Token` varchar(100) CHARACTER SET latin1 NOT NULL,
  `LoginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TECHTEST.Movies definition
CREATE TABLE `Movies` (
  `Id` char(10) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Overview` varchar(200) DEFAULT NULL,
  `ShowBeginDate` date NOT NULL,
  `ShowEndDate` date NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- TECHTEST.UserInfo definition
CREATE TABLE `UserInfo` (
  `UserId` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `CreaetDate` date NOT NULL,
  `DelFlag` char(1) NOT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO TECHTEST.UserInfo (UserId,Password,Name,CreaetDate,DelFlag) VALUES
     ('user1','passwd1','テスト　太郎','2023-04-23','0'),
     ('user2','passwd2','テスト　次郎','2023-04-20','1');

INSERT INTO TECHTEST.Movies (Id,Name,Overview,ShowBeginDate,ShowEndDate) VALUES
     ('Cate100001','THE FIRST SLAM DUNK',NULL,'2023-04-01','2023-04-30'),
     ('Cate100002','名探偵コナン 黒鉄の魚影',NULL,'2023-04-14','2023-05-13'),
     ('Cate100003','ザ・スーパーマリオブラザーズ・ムービー',NULL,'2023-04-28','2023-05-30'),
     ('Cate100004','聖闘士星矢 The Beginning',NULL,'2023-04-28','2023-05-30'),
     ('Cate100005','ブラックパンサー／ワカンダ・フォーエバー',NULL,'2023-03-29','2023-04-20');
