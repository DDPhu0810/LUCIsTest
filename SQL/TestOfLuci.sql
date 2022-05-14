-- Drop the database if it already exists
DROP DATABASE IF EXISTS TestOfLuci;

-- Create database
CREATE DATABASE IF NOT EXISTS TestOfLuci;
USE TestOfLuci;

-- create table: Department
DROP TABLE IF EXISTS Department;
CREATE TABLE Department(
	DepartmentID 			TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    DepartmentName 			NVARCHAR(30) NOT NULL UNIQUE KEY
);

-- create table: Account
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
	AccountID				TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Email					VARCHAR(50) NOT NULL UNIQUE KEY, -- Cannot update this field
    `Password` 				VARCHAR(800) NOT NULL,
    Username				VARCHAR(50) NOT NULL UNIQUE KEY, -- Cannot update this field
    FirstName				NVARCHAR(50) NOT NULL,
    LastName				NVARCHAR(50) NOT NULL,	-- create field fullName in POJO
    DepartmentID 			TINYINT UNSIGNED NOT NULL,	-- Set default waiting
    CreateDate				DATETIME DEFAULT NOW(), -- Cannot update this field
    FOREIGN KEY(DepartmentID) REFERENCES Department(DepartmentID)
);

/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data Department
INSERT INTO Department(DepartmentName) 
VALUES
						(N'Marketing'	),
						(N'Sale'		),
						(N'Bảo vệ'		),
						(N'Nhân sự'		),
						(N'Kỹ thuật'	),
						(N'Tài chính'	),
						(N'Phó giám đốc'),
						(N'Giám đốc'	),
						(N'Thư kí'		),
						(N'Bán hàng'	);
                        
-- Add data Account
INSERT INTO `Account`(Email				,							`Password`							,	Username	, FirstName	,LastName			,DepartmentID	, CreateDate)
VALUES 				('aaa@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'aaa'		,'A'		,'Nguyen Hai'		,'5'			,'2020-03-05'),
					('bbb@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'bbb'		,'B'		,'Tong Quang'		,'1'			,'2020-03-05'),
                    ('ccc@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'ccc'		,'C'		,'Nguyen Van'		,'2'			,'2020-03-07'),
                    ('ddd@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'ddd'		,'D'		,'Duong'			,'3'			,'2020-03-08'),
                    ('eee@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'eee'		,'E'		,'Nguyen Chien' 	,'4'			,'2020-03-10'),
                    ('fff@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'fff'		,'F'		,'Ngo Ba'			,'6'			,NOW()),
                    ('ggg@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'ggg'		,'G'		,'Bui Xuan'			,'7'			,NOW()),
                    ('hhh@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'hhh'		,'H'		,'Nguyen Thanh'		,'8'			,'2020-04-07'),
                    ('iii@gmail.com'	,'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'iii'		,'I'		,'Duong Van'		,'9'			,'2020-04-07'),
                    ('phu0810@gmail.com','$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	'phu0810'	,'Phu'		,'Duong Doan'		,'10'			,'2020-04-09');
