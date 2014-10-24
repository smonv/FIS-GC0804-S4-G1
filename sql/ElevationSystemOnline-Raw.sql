create database ElevationSystemOnline
go

use ElevationSystemOnline
go

create table clients(
	cid int primary key identity,
	name nvarchar(100),
	username nvarchar(60) unique not null,
	email nvarchar(254) unique not null,
	password nvarchar(254),
	contact_details text,
	address nvarchar(max),
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
	name nvarchar(254) not null,
	cid int foreign key references categories(cid),
	infomations text,
	price decimal,
	image_path nvarchar(max),
	create_at datetime
)

create table payment_types(
	ptid int primary key identity,
	name nvarchar(254) not null
)

create table order_status(
	osid int identity primary key,
	name nvarchar(254) not null,
)

create table orders(
	oid int identity primary key,
	cid int foreign key references clients(cid),
	number nvarchar(254) not null,
	product_id int foreign key references products(pid),
	location_name nvarchar(254) not null,
	address nvarchar(max) not null,
	quantity int,
	order_status int foreign key references order_status(osid),
	payment_type int foreign key references payment_types(ptid),
	create_at datetime,
	update_at datetime
)

create table complaints(
	cid int identity primary key,
	oid int foreign key references orders(oid),
	problem text,
	create_at datetime,
)

create table feedback_level(
	flid int identity primary key,
	name nvarchar(254) not null
)

create table feedbacks(
	fid int identity primary key,
	name nvarchar(254) not null,
	email nvarchar(254) not null,
	description text not null,
	feedback_level int foreign key references feedback_level(flid),
	problem text,
	improvement text,
	create_at datetime
)

create table admins(
	aid int identity primary key,
	name nvarchar(254) not null,
	username nvarchar(60) not null,
	email nvarchar(254) not null,
	password nvarchar(254) not null,
	is_super bit,
	is_manager bit,
	create_at datetime,
	update_at datetime
)

create table projects(
	pid int identity primary key,
	title nvarchar(254) not null,
	content text not null,
	location nvarchar(254),
	image_path nvarchar(max),
	start_at date,
	end_at date,
)