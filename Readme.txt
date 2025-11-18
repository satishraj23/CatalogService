create database productms;
use productms;

create table products
 (product_id INT auto_increment primary key,name varchar(70),brand varchar(50),price double(8,2),color varchar(20),size varchar(10),description varchar(80),categoryName varchar(50));
 
 
INSERT INTO products (name, brand, price, color, size, description,categoryName )
VALUES ('T-Shirt', 'Nike', 1299.50, 'Blue', 'S','Dresses','Clothing');

