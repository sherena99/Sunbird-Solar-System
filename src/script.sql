DROP DATABASE IF EXISTS SunBird;
CREATE DATABASE IF NOT EXISTS SunBird;
SHOW DATABASES ;
USE SunBird;
#-------------------


DROP TABLE IF EXISTS Customer;
CREATE TABLE IF NOT EXISTS Customer(
    custID VARCHAR(15),
    custTitle VARCHAR(15),
    custName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    custAddress TEXT,
    NIC VARCHAR(20),
    mobileNum INTEGER(20),
    email VARCHAR(20),
    CONSTRAINT PRIMARY KEY (custID, mobileNum)
    );
SHOW TABLES ;
DESCRIBE Customer;
#---------------------

DROP TABLE IF EXISTS CRO;
CREATE TABLE IF NOT EXISTS CRO(
    croId VARCHAR(15),
    croName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    croAddress text,
    croCode VARCHAR(35),
    croMobile VARCHAR (20),
    croSalary DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (croId)
    );
SHOW TABLES ;
DESCRIBE CRO;
#---------------------
DROP TABLE IF EXISTS Site;
CREATE TABLE IF NOT EXISTS Site(
    date DATE,
    time VARCHAR(25),
    inquiryId VARCHAR(15),
    address VARCHAR (60),
    type VARCHAR(25),
    roof VARCHAR(25),
    direction VARCHAR(25),
    ExtraWork VARCHAR(50),
    charges DOUBLE DEFAULT 0.00,
    note text,
    CONSTRAINT PRIMARY KEY (inquiryId),
    CONSTRAINT FOREIGN KEY (inquiryId) REFERENCES Inquiry(inquiryCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Site;
#---------------------

DROP TABLE IF EXISTS Inquiry;
CREATE TABLE IF NOT EXISTS Inquiry(
    inquiryCode VARCHAR(15),
    time VARCHAR(15),
    date DATE,
    cId VARCHAR(15),
    custName VARCHAR (30),
    siteAddress VARCHAR (40),
    croIds  VARCHAR(15),
    croCode VARCHAR (30),
    inquiryFrom VARCHAR(15),
    Remarks VARCHAR(40),
    total DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (inquiryCode),
    CONSTRAINT FOREIGN KEY (cId) REFERENCES Customer(custID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (croIds) REFERENCES CRO(croId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Inquiry;
#-----------------------


DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS Product(
    productCode VARCHAR(15),
    productName VARCHAR(15),
    description TEXT,
    discount INT DEFAULT 0,
    listPrice DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (productCode)
    );
SHOW TABLES ;
DESCRIBE Product;
#-----------------------

DROP TABLE IF EXISTS InquiryDetails;
CREATE TABLE IF NOT EXISTS InquiryDetails(
    inquiryId VARCHAR (15),
    productCode VARCHAR(15),
    inquiryQty INTEGER,
    discount DOUBLE,
    CONSTRAINT PRIMARY KEY (productCode, inquiryId),
    CONSTRAINT FOREIGN KEY (productCode) REFERENCES Product(productCode) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (inquiryId) REFERENCES Inquiry(inquiryCode) ON DELETE CASCADE ON UPDATE CASCADE

    );
SHOW TABLES ;
DESCRIBE InquiryDetails;
#-----------------------

DROP TABLE IF EXISTS Payment;
CREATE TABLE IF NOT EXISTS Payment(
    paymentId VARCHAR (15),
    date DATE,
    time VARCHAR(25),
    inquiryId VARCHAR(15),
    customerName VARCHAR (30),
    croCode VARCHAR (40),
    paymentMethod VARCHAR (30),
    total DOUBLE DEFAULT 0.00,
    totalDiscount DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (paymentId),
    CONSTRAINT FOREIGN KEY (inquiryId) REFERENCES Inquiry (inquiryCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE Payment;

#-----------------------

DROP TABLE IF EXISTS CustomerComplain;
CREATE TABLE IF NOT EXISTS CustomerComplain(
    complainNum VARCHAR(15),
    time VARCHAR(15),
    date DATE,
    inquiryId VARCHAR(15),
    custName VARCHAR(30),
    croCode VARCHAR(30),
    comAbout VARCHAR(30),
    comDetails TEXT,
    CONSTRAINT PRIMARY KEY (complainNum),
    CONSTRAINT FOREIGN KEY (inquiryId) REFERENCES Inquiry(inquiryCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE CustomerComplain;


#-----------------------------------------------------------------


DROP TABLE IF EXISTS UserManagement;
CREATE TABLE IF NOT EXISTS UserManagement(
    userId VARCHAR (15),
    userPassword VARCHAR (15),
    CONSTRAINT PRIMARY KEY (userId, userPassword)
    );
SHOW TABLES ;
DESCRIBE UserManagement;

DROP TABLE IF EXISTS UserReceptionist;
CREATE TABLE IF NOT EXISTS UserReceptionist(
    userId VARCHAR (15),
    userPassword VARCHAR (15),
    CONSTRAINT PRIMARY KEY (userId, userPassword)
    );
SHOW TABLES ;
DESCRIBE UserReceptionist;
--------------------------------------------------------------------------------------------------------------------------------------













