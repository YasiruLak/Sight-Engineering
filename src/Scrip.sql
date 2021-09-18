DROP DATABASE IF EXISTS sight;
CREATE DATABASE IF NOT EXISTS sight;
SHOW DATABASES ;
USE sight;

DROP TABLE IF EXISTS supplier;
CREATE TABLE IF NOT EXISTS supplier(
    id VARCHAR(15),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    mobile VARCHAR(15),
    email VARCHAR(45),
    CONSTRAINT PRIMARY KEY (id)
    );
SHOW TABLES ;
DESCRIBE supplier;

DROP TABLE IF EXISTS Vehicle;
CREATE TABLE IF NOT EXISTS Vehicle(
    VehicleNo VARCHAR(20),
    description TEXT,
    type VARCHAR(15),
    CONSTRAINT PRIMARY KEY (VehicleNo)
    );
SHOW TABLES ;
DESCRIBE Vehicle;

DROP TABLE IF EXISTS manager;
CREATE TABLE IF NOT EXISTS manager(
    nic VARCHAR(15),
    name VARCHAR(45) NOT NULL,
    status VARCHAR(10),
    age VARCHAR (10),
    address TEXT,
    mobile VARCHAR(15),
    email VARCHAR(45),
    CONSTRAINT PRIMARY KEY (nic)
    );
SHOW TABLES ;
DESCRIBE manager;

DROP TABLE IF EXISTS employee;
CREATE TABLE IF NOT EXISTS employee(
    empId VARCHAR(15),
    name VARCHAR(45) NOT NULL,
    type VARCHAR(25),
    age VARCHAR(10),
    address VARCHAR (35),
    city VARCHAR (15),
    province VARCHAR (15),
    contact VARCHAR(15),
    dailySalary DOUBLE DEFAULT 0.00,
    CONSTRAINT PRIMARY KEY (empId)
    );
SHOW TABLES ;
DESCRIBE employee;

DROP TABLE IF EXISTS item;
CREATE TABLE IF NOT EXISTS item(
    itemCode VARCHAR(15),
    name VARCHAR(20),
    description TEXT,
    size VARCHAR(15),
    qtyOnHand VARCHAR (20) DEFAULT 0,
    CONSTRAINT PRIMARY KEY (itemCode)
    );
SHOW TABLES ;
DESCRIBE item;

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order`(
    orderId VARCHAR(15),
    sid VARCHAR(15),
    orderDate DATE,
    time VARCHAR(15),
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (sid) REFERENCES supplier(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `order`;

DROP TABLE IF EXISTS `order detail`;
CREATE TABLE IF NOT EXISTS `order detail`(
    iCode VARCHAR(15),
    oId VARCHAR(15),
    qty INT,
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (iCode, oId),
    CONSTRAINT FOREIGN KEY (iCode) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (oId) REFERENCES `order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE `order detail`;

DROP TABLE IF EXISTS attendance;
CREATE TABLE IF NOT EXISTS attendence(
    attendId VARCHAR(15),
    eId VARCHAR(15),
    name VARCHAR(15),
    catogry VARCHAR(15),
    inDate DATE,
    inTime VARCHAR(15),
    outDate DATE,
    outTime VARCHAR(15),
    status VARCHAR(15),
    CONSTRAINT PRIMARY KEY (attendId, eId),
    CONSTRAINT FOREIGN KEY (eId) REFERENCES employee(empId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE attendence;

DROP TABLE IF EXISTS `material detail`;
CREATE TABLE IF NOT EXISTS `material detail`(
    iId VARCHAR(15),
    eId VARCHAR(15),
    aId VARCHAR(15),
    qty VARCHAR(15),
    status VARCHAR(15),
    receiveQty VARCHAR(15),
    CONSTRAINT PRIMARY KEY (aId, eId, iId),
    CONSTRAINT FOREIGN KEY (eId) REFERENCES employee(empId) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (aId) REFERENCES attendence(attendId) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (iId) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE `material detail`;

DROP TABLE IF EXISTS payment;
CREATE TABLE IF NOT EXISTS payment(
    paymentId VARCHAR(15),
    orderId VARCHAR(15),
    supplierId VARCHAR (15),
    orderDate DATE,
    paymentDate DATE,
    paymentTime VARCHAR (15),
    amount DOUBLE,
    payMethod VARCHAR (15),
    invoiceNo VARCHAR (25),
    CONSTRAINT PRIMARY KEY (paymentId),
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES `order`(orderId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (supplierId) REFERENCES supplier (id) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE payment;


"SELECT * FROM manager"
"INSERT INTO manager VALUES (?,?,?,?,?,?,?)"
"SELECT * FROM manager WHERE nic=?"
"UPDATE manager SET name=?, status=?, age=?, address=?, mobile=?, email=? WHERE nic=? "
"DELETE FROM manager WHERE nic=" +"'"+managerNic+"'"
