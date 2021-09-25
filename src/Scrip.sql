DROP DATABASE IF EXISTS sight_engineering;
CREATE DATABASE IF NOT EXISTS sight_engineering;
SHOW DATABASES ;
USE sight_engineering;

DROP TABLE IF EXISTS login;
CREATE TABLE IF NOT EXISTS login(
    nic VARCHAR(15) NOT NULL,
    userName VARCHAR(25) NOT NULL,
    password VARCHAR (25) NOT NULL,
    role VARCHAR(10) NOT NULL,
    CONSTRAINT PRIMARY KEY (nic)
    );
SHOW TABLES ;
DESCRIBE login;

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
    qtyOnHand INT (20) DEFAULT 0,
    CONSTRAINT PRIMARY KEY (itemCode)
    );
SHOW TABLES ;
DESCRIBE item;

DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders(
    orderId VARCHAR(15),
    sid VARCHAR(15),
    orderDate DATE,
    time TIME,
    cost DOUBLE,
    CONSTRAINT PRIMARY KEY (orderId),
    CONSTRAINT FOREIGN KEY (sid) REFERENCES supplier(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE orders;

DROP TABLE IF EXISTS order_detail;
CREATE TABLE IF NOT EXISTS order_detail(
    iCode VARCHAR(15),
    oId VARCHAR(15),
    qty INT,
    price DOUBLE,
    CONSTRAINT PRIMARY KEY (iCode, oId),
    CONSTRAINT FOREIGN KEY (iCode) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (oId) REFERENCES orders(orderId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE order_detail;

DROP TABLE IF EXISTS attendance;
CREATE TABLE IF NOT EXISTS attendance(
    attendId VARCHAR(15),
    eId VARCHAR(15),
    inDate DATE,
    inTime TIME,
    outDate DATE,
    outTime TIME,
    status VARCHAR(15),
    CONSTRAINT PRIMARY KEY (attendId),
    CONSTRAINT FOREIGN KEY (eId) REFERENCES employee(empId) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES ;
DESCRIBE attendance;

DROP TABLE IF EXISTS item_detail;
CREATE TABLE IF NOT EXISTS item_detail(
    iId VARCHAR(15),
    name VARCHAR (15),
    aId VARCHAR(15),
    qty INT(15),
    status VARCHAR(15),
    receiveQty INT(15),
    CONSTRAINT PRIMARY KEY (aId, iId),
    CONSTRAINT FOREIGN KEY (aId) REFERENCES attendance(attendId) ON DELETE CASCADE ON UPDATE CASCADE ,
    CONSTRAINT FOREIGN KEY (iId) REFERENCES item(itemCode) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE item_detail;

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
    CONSTRAINT FOREIGN KEY (orderId) REFERENCES orders(orderId) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (supplierId) REFERENCES supplier (id) ON DELETE CASCADE ON UPDATE CASCADE
    );
SHOW TABLES;
DESCRIBE payment;


"SELECT * FROM manager"
"INSERT INTO manager VALUES (?,?,?,?,?,?,?)"
"SELECT * FROM manager WHERE nic=?"
"UPDATE manager SET name=?, status=?, age=?, address=?, mobile=?, email=? WHERE nic=? "
"DELETE FROM manager WHERE nic=" +"'"+managerNic+"'"
