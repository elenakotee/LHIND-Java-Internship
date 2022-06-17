-- Query 1

select * from customers where CustomerNumber in (
	select customerNumber
	from orders
	group by CustomerNumber
	having count(*) = (
		select max(temp.CNT)
		from (
			select count(*) as cnt
			from orders
			group by customerNumber
			) as temp
	)
);


-- Query 2
select customers.customerName, orderdetails.* 
from customers 
inner join orders 
on customers.customerNumber=orders.customerNumber 
inner join orderdetails
on orders.orderNumber=orderdetails.orderNumber 
and customers.country="Germany" ;


-- Query 3
select employees.*, SUM(payments.amount) from
employees inner join customers
on employees.employeeNumber = customers.salesRepEmployeeNumber
inner join payments
on payments.customerNumber = customers.customerNumber
group by employeeNumber;

-- Query 4
select products.productCode, products.productName, orders.orderDate from
products inner join orderdetails
on products.productCode = orderdetails.productCode
inner join orders
on orderdetails.orderNumber = orders.orderNumber
where orders.orderDate 
between date_sub('2005-01-01', interval 1 month) and '2005-01-01';



-- Query 5
use classicmodels;
drop table if exists classicmodels.employeedetails ;

create table classicmodels.employeedetails(
employeeNumber int not null,
bankAccount varchar(50) not null,
address varchar(100),
phoneNumber int not null,
personalEmail varchar(50) not null,

primary key(employeeNumber),
constraint FK_employeeNumber foreign key (employeeNumber)
references employees(employeeNumber)
);