create database ElevationSystemOnline
go

use ElevationSystemOnline
go

create table clients(
	cid int primary key identity,
	name nvarchar(100) not null,
	username nvarchar(60) unique not null,
	email nvarchar(254) unique not null,
	password_digest nvarchar(254),
	contact_details text,
	client_address nvarchar(max),
	company_name nvarchar(max),
	create_at datetime,
	update_at datetime
)

create table categories(
	cid int identity primary key,
	name nvarchar(254) not null
)

create table products(
	pid int identity primary key,
	code nvarchar(10) not null,
	name nvarchar(254) not null,
	category_id int foreign key references categories(cid),
	manufacturer nvarchar(254),
	produced_in nvarchar(254),
	size nvarchar(254),
	infomations text,
	price decimal,
	construction_price decimal,
	construction_time int,
	image_path nvarchar(max),
	create_at datetime,
	update_at datetime
)

create table payment_types(
	ptid int primary key identity,
	name nvarchar(254) not null
)

create table list_status(
	lsid int identity primary key,
	name nvarchar(254) not null,
)

create table orders(
	oid int identity primary key,
	client_id int foreign key references clients(cid),
	number nvarchar(254) not null,
	location_name nvarchar(254) not null,
	location_address nvarchar(max) not null,
	order_status int foreign key references list_status(lsid),
	payment_type int foreign key references payment_types(ptid),
	start_at datetime,
	create_at datetime,
	update_at datetime
)

create table order_product_details(
	opdid int identity primary key,
	order_id int foreign key references orders(oid),
	product_id int foreign key references products(pid),
	quantity int,
	create_at datetime
)

create table complaints(
	cid int identity primary key,
	order_id int foreign key references orders(oid),
	problem text,
	complaint_status int foreign key references list_status(lsid),
	create_at datetime,
	update_at datetime
)

create table feedback_level(
	flid int identity primary key,
	name nvarchar(254) not null
)

create table feedbacks(
	fid int identity primary key,
	name nvarchar(254) not null,
	email nvarchar(254) not null,
	feedback_description text not null,
	feedback_level int foreign key references feedback_level(flid),
	problem text,
	improvement text,
	feedback_status int foreign key references list_status(lsid),
	create_at datetime,
	update_at datetime
)

create table admins(
	aid int identity primary key,
	name nvarchar(254) not null,
	username nvarchar(60) not null,
	email nvarchar(254) not null,
	password_digest nvarchar(254) not null,
	is_super bit,
	is_manager bit,
	create_at datetime,
	update_at datetime
)

create table projects(
	pid int identity primary key,
	order_id int foreign key references orders(oid),
	title nvarchar(254) not null,
	content text not null,
	image_path nvarchar(max),
	start_at date,
	end_at date,
)

insert into categories values('cylindrical')
insert into categories values('Rectangular')

insert into products values('EVE1', 'Elevator 1', 1,'Cu Beo','Viet Nam','1 x 1 x 1', 'fast and stable', 123,10, 1,null, GETDATE(),null)
insert into products values('EVE2', 'Elevator 2', 1,'Cu Beo','Viet Nam','2 x 2 x 2', 'fast and stable', 312,20, 2,null, GETDATE(),null)
insert into products values('EVE3', 'Elevator 3', 1,'Cu Beo','Viet Nam','3 x 3 x 3', 'fast and stable', 234,30, 3,null, GETDATE(),null)
insert into products values('EVE4', 'Elevator 4', 2,'Cu Beo','Viet Nam','4 x 4 x 4', 'fast and stable', 432,40, 4,null, GETDATE(),null)
insert into products values('EVE5', 'Elevator 5', 2,'Cu Beo','Viet Nam','5 x 5 x 5', 'fast and stable', 345,50, 5,null, GETDATE(),null)
insert into products values('EVE6', 'Elevator 6', 2,'Cu Beo','Viet Nam','6 x 6 x 6', 'fast and stable', 543,60, 6,null, GETDATE(),null)
---insert into products values('EVE12', 'Elevator 1', 1, 'fast and stable', 123, null, GETDATE())
---insert into products values('EVE25', 'Elevator 2', 1, 'fast and stable', 312, null, GETDATE())
--insert into products values('EVE34', 'Elevator 3', 1, 'fast and stable', 234, null, GETDATE())
--insert into products values('EVE48', 'Elevator 4', 2, 'fast and stable', 432, null, GETDATE())
--insert into products values('EVE59', 'Elevator 5', 2, 'fast and stable', 345, null, GETDATE())
--insert into products values('EVE67', 'Elevator 6', 2, 'fast and stable', 543, null, GETDATE())

insert into payment_types values('Cash')
insert into payment_types values('Bank Transfer')

insert into list_status values('Pending')
insert into list_status values('Proccess')
insert into list_status values('Completed')