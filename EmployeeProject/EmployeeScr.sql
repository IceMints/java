CREATE DATABASE employees;
USE employees;

-- -----------------------------------------------------
-- Table `employees`.`departments`
-- -----------------------------------------------------


CREATE TABLE `employees`.`departments` (
  `department_id` INT(11) NOT NULL AUTO_INCREMENT,
  `department_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`department_id`));
  

-- -----------------------------------------------------
-- Table `employees`.`projects`
-- -----------------------------------------------------

CREATE TABLE `employees`.`projects` (
  `project_id` INT(11) NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`project_id`));
 
  
 
-- -----------------------------------------------------
-- Table `employees`.`employee`
-- -----------------------------------------------------


CREATE TABLE `employees`.`employee` (
  `employee_id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(80) NOT NULL,
  `last_name` VARCHAR(80) NOT NULL,
  `salary` DECIMAL(13,2) NULL DEFAULT NULL,
  `position` VARCHAR(45) NULL DEFAULT NULL,
  `address1` VARCHAR(60) NOT NULL,
  `address2` VARCHAR(60) NULL DEFAULT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` CHAR(2) NOT NULL,
  `zipcode` VARCHAR(10) NOT NULL,
  `department_id` INT(11) NOT NULL,
  `project_id` INT(11) NOT NULL,
  PRIMARY KEY (`employee_id`));
  
  ALTER Table employees.employee
    ADD FOREIGN KEY (`department_id`)
    REFERENCES `employees`.`departments` (`department_id`);
ALTER TABLE employees.employee
    ADD FOREIGN KEY (`project_id`)
    REFERENCES `employees`.`projects` (`project_id`);

-- -----------------------------------------------------
-- INSERTING INTO PROJECTS table
-- -----------------------------------------------------
 
INSERT INTO employees.projects (project_name)
VALUES 
('TURFY'),
('SUPER'),
('MOUSAI'),
('WRANGLER'),
('DONATELLO');

-- -----------------------------------------------------
-- INSERTING INTO DEPARTMENT table
-- -----------------------------------------------------

INSERT INTO employees.departments (department_name)
VALUES 
('Admin'),
('HR'),
('Engineering'),
('Marketing'),
('Sales');
	  
-- -----------------------------------------------------
-- INSERTING INTO EMPLOYEE table
-- -----------------------------------------------------	  

INSERT INTO `employees`.`employee`
(`first_name`,`last_name`,`salary`,`position`,
`address1`,`address2`,`city`,`state`,`zipcode`,`department_id`,`project_id`)

VALUES
('John','Doe','100000.00','CEO',
'123 Mongomery Lane','apt 1','Sandalwood','CA','91006', '1', '2'),

('Danny','Kim','40000.00','Initiate',
'123 Mongomery Lane','apt 2','Sandalwood','CA','91006','3','5'),

('Sandy','Lu','60000.00','Manager',
'123 Mongomery Lane','apt 3','Sandalwood','CA','91006','2','1'),

('Jane','Doe','80000.00','CFO',
'123 Mongomery Lane','apt 1','Sandalwood','CA','91006','1','2');


-- -----------------------------------------------------
-- Table `employees`.`users`
-- -----------------------------------------------------

CREATE TABLE `employees`.`users` (
  `user_name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_name`));

INSERT INTO `employees`.`users`(`user_name`,`password`)
    VALUE('admin','monkey');