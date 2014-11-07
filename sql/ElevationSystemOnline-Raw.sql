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
	price decimal(16,2),
	construction_price decimal(16,2),
	construction_time int,
	create_at datetime,
	update_at datetime
)

create table product_informations(
	pinfoid int identity primary key,
	product_id int foreign key references products(pid) unique,
	manufacturer nvarchar(254),
	produced_in nvarchar(254),
	size nvarchar(254),
	informations text,
	e_load nvarchar(254),
	speed nvarchar(254),
	feature_1 nvarchar(max),
	feature_2 nvarchar(max),
	feature_3 nvarchar(max),
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
	floors int,
	height_of_floor decimal(16,2),
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
	client_id int foreign key references clients(cid),
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

insert into categories values('Passenger')
insert into categories values('Home')
insert into categories values('Vehicle')
insert into categories values('Medical')
insert into categories values('Freight')
insert into categories values('Observation')
insert into categories values('Dumbwaiter')

insert into products values('AM', 'Anti Mage', 1, 15400, 1540, 1, GETDATE(),null)
insert into products values('TBS', 'Timbersaw', 2, 10000, 1000, 1, GETDATE(),null)
insert into products values('BM', 'Beastmaster', 3, 40000, 4000, 2, GETDATE(),null)
insert into products values('DS', 'Dwarven Sniper', 4, 16700, 1670, 2, GETDATE(),null)
insert into products values('ES', 'Earthshaker', 5, 12000, 1200, 2, GETDATE(),null)
insert into products values('WR', 'Windranger', 6, 19000, 1900, 1, GETDATE(),null)
insert into products values('POTM', 'Priestess of The Moon', 7, 4200, 420, 1, GETDATE(),null)

insert into product_informations values(1, 'Uzumaki Naruto', 'Germany', '2.5 x 2 x 2.5', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '600-1000kg', '1.5-2m/s', null, null, null, null, GETDATE(), null)
insert into product_informations values(2, 'Hyuga Neji', 'Japan', '2 x 2 x 2.5', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '300-400kg', '1-2m/s', null, null, null, null, GETDATE(), null)
insert into product_informations values(3, 'Rock Lee', 'United Kingdom', '4 x 4 x 4', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '3000-5000kg', '1.5-2.5m/s', null, null, null, null, GETDATE(), null)
insert into product_informations values(4, 'Hatake Kakashi', 'Canada', '4 x 3 x 3', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '600-1000kg', '1-2m/s', null, null, null, null, GETDATE(), null)
insert into product_informations values(5, 'Killer Bee', 'Italy', '3 x 2 x 3.5', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '1500-2000kg', '1.5-3m/s', null, null, null, null, GETDATE(), null)
insert into product_informations values(6, 'Uchiha Sasuke', 'Spain', '1.5 x 1.5 x 4', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '500-800kg', '1-2m/s', null, null, null, null, GETDATE(), null)
insert into product_informations values(1, 'Senju Tobirama', 'France', '1.5 x 1 x 1.5', 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry is standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.', '200kg', '2m/s', null, null, null, null, GETDATE(), null)

insert into payment_types values('Cash')
insert into payment_types values('Bank Transfer')

insert into list_status values('Pending')
insert into list_status values('Proccess')
insert into list_status values('Completed')

insert into feedback_level values('Good')
insert into feedback_level values('Average')
insert into feedback_level values('Satisfactory')
insert into feedback_level values('Poor')
