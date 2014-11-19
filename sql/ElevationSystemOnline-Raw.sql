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

create table nations(
	nid int identity primary key,
	name nvarchar(254)
)

create table manufacturers(
	mid int identity primary key,
	name nvarchar(254),
	nation_id int foreign key references nations(nid),
)

create table images(
	img_id int primary key identity,
	img_path nvarchar(max),
	img_size int,
	create_at datetime,
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
	model_no nvarchar(254),
	manufacturer_id int foreign key references manufacturers(mid),
	size nvarchar(254),
	informations text,
	e_load int,
	speed decimal(16,2),
	feature_1 nvarchar(max),
	feature_2 nvarchar(max),
	feature_3 nvarchar(max),
	create_at datetime,
	update_at datetime
)

create table product_images(
	product_img_id int primary key identity,
	product_id int foreign key references products(pid),
	img_id int foreign key references images(img_id)
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

create table contracts(
	cid int primary key identity,
	admin_id int foreign key references admins(aid),
	order_id int foreign key references orders(oid) unique,
	client_name nvarchar(254),
	client_email nvarchar(254),
	client_phone nvarchar(20),
	client_requirements text,
	payment_details text,
	total_product_price decimal(16,2),
	total_construction_price decimal(16,2),
	total_construction_time int,
	create_at datetime,
	update_at datetime
)

create table projects(
	pid int identity primary key,
	admin_id int foreign key references admins(aid),
	contract_id int foreign key references contracts(cid) unique,
	title nvarchar(254) not null,
	content text not null,
	project_status int foreign key references list_status(lsid),
	img_id int foreign key references images(img_id),
	start_at datetime,
	end_at datetime,
	is_public bit,
	create_at datetime,
	update_at datetime,
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

insert into admins values('Admin', 'admintest', 'sonndgc00548@fpt.edu.vn', 'fBz0haBNxMPm1iscRZ171g8ZbPI=_caz3leGlBng=', 1, 1, GETDATE(), null)

insert into nations values('United State')
insert into nations values('United Kingdom')
insert into nations values('Japan')
insert into nations values('Germany')
insert into nations values('France')
insert into nations values('Australia')

insert into manufacturers values('KONE', 1)
insert into manufacturers values('OTIS', 2)
insert into manufacturers values('MITSUBISHI', 3)
insert into manufacturers values('CENE', 4)
insert into manufacturers values('SGS', 5)
insert into manufacturers values('SUXUN', 6)

insert into categories values('Passenger')
insert into categories values('Home')
insert into categories values('Vehicle')
insert into categories values('Medical')
insert into categories values('Freight')
insert into categories values('Observation')
insert into categories values('Dumbwaiter')

insert into products values('AM', 'AM01-ELE', 1, 15400, 1540, 1, GETDATE(),null)
insert into products values('DR', 'DR02-ELE', 1, 16800, 1680, 1, GETDATE(),null)
insert into products values('NA', 'NA03-LIFT', 1, 14500, 1450, 1, GETDATE(),null)
insert into products values('SS', 'SS04-ELE', 1, 16200, 1620, 1, GETDATE(),null)
insert into products values('TW', 'TW05-LIFT', 1, 15800, 1580, 1, GETDATE(),null)
insert into products values('RK', 'RK06-ELE', 1, 14900, 1490, 1, GETDATE(),null)
insert into products values('TBS', 'TBS07-LIFT', 2, 10000, 1000, 1, GETDATE(),null)
insert into products values('NCP', 'NCP08-ELE', 2, 11000, 1100, 1, GETDATE(),null)
insert into products values('FLV', 'FLV09-ELE', 2, 10800, 1080, 1, GETDATE(),null)
insert into products values('GM', 'GM10-LIFT', 2, 12100, 1210, 1, GETDATE(),null)
insert into products values('OM', 'OM11-LIFT', 2, 11300, 1130, 1, GETDATE(),null)
insert into products values('WD', 'WD12-ELE', 2, 12000, 1200, 1, GETDATE(),null)
insert into products values('BM', 'BM13-ELE', 3, 40000, 4000, 2, GETDATE(),null)
insert into products values('GT', 'GT14-LIFT', 3, 33000, 3300, 2, GETDATE(),null)
insert into products values('WK', 'WK15-ELE', 3, 35000, 3500, 2, GETDATE(),null)
insert into products values('MP', 'MP16-ELE', 3, 37300, 3730, 2, GETDATE(),null)
insert into products values('TH', 'TH17-ELE', 3, 37800, 3780, 2, GETDATE(),null)
insert into products values('SM', 'SM18-LIFT', 3, 36500, 3650, 2, GETDATE(),null)
insert into products values('DS', 'DS19-ELE', 4, 16700, 1670, 2, GETDATE(),null)
insert into products values('PL', 'PL20-ELE', 4, 17300, 1730, 2, GETDATE(),null)
insert into products values('NS', 'NS21-LIFT', 4, 18500, 1850, 2, GETDATE(),null)
insert into products values('BH', 'BH22-LIFT', 4, 17900, 1790, 2, GETDATE(),null)
insert into products values('BB', 'BB23-ELE', 4, 16200, 1620, 2, GETDATE(),null)
insert into products values('BS', 'BS24-ELE', 4, 18400, 1840, 2, GETDATE(),null)
insert into products values('ES', 'ES25-LIFT', 5, 12000, 1200, 2, GETDATE(),null)
insert into products values('TP', 'TP26-ELE', 5, 15000, 1500, 2, GETDATE(),null)
insert into products values('ET', 'ET27-ELE', 5, 15600, 1560, 2, GETDATE(),null)
insert into products values('LD', 'LD28-LIFT', 5, 15200, 1520, 2, GETDATE(),null)
insert into products values('NW', 'NW29-LIFT', 5, 14300, 1430, 2, GETDATE(),null)
insert into products values('SB', 'SB30-ELE', 5, 13100, 1310, 2, GETDATE(),null)
insert into products values('WR', 'WR31-ELE', 6, 19000, 1900, 1, GETDATE(),null)
insert into products values('DK', 'DK32-LIFT', 6, 20000, 2000, 1, GETDATE(),null)
insert into products values('PA', 'PA33-ELE', 6, 19700, 1970, 1, GETDATE(),null)
insert into products values('DP', 'DP34-ELE', 6, 19500, 1950, 1, GETDATE(),null)
insert into products values('SD', 'SD35-LIFT', 6, 20400, 2040, 1, GETDATE(),null)
insert into products values('AA', 'AA36-ELE', 6, 20800, 2080, 1, GETDATE(),null)
insert into products values('POTM', 'POTM37-ELE', 7, 4200, 420, 1, GETDATE(),null)
insert into products values('MR', 'MR38-LIFT', 7, 4400, 440, 1, GETDATE(),null)
insert into products values('LS', 'LS39-LIFT', 7, 5000, 500, 1, GETDATE(),null)
insert into products values('TA', 'TA40-LIFT', 7, 4700, 470, 1, GETDATE(),null)
insert into products values('QOP', 'QOP41-ELE', 7, 5100, 510, 1, GETDATE(),null)
insert into products values('OD', 'OD42-ELE', 7, 4500, 450, 1, GETDATE(),null)

insert into product_informations values(1, 'AM01', 1, '2.5 x 2 x 2.5', 'Manufacturers choose rare earth material and fully combine coaxial transmission technology and digital variable frequency technology with group computer combined control technology. With less running cost & less energy dissipated. Compared with the ordinary and traditional technology, gearless traction machine can save more than 40% of the energy. The motor is developed base on technology of Canada.', 1000, 2.00, 'Fast, save and stable', 'Full safety features available as an option', 'High energy saving', GETDATE(), null)
insert into product_informations values(2, 'DR02', 2, '3 x 2.5 x 3', 'Designed using modern techniques and quiet machine technology of Japanese. This is the first level of above ground jack configuration available, and it features only one section of plunger that extends out of the cylinder. It has automatic control center so it can save more than 35% energy. It is a perfect fit for skyscrapers and malls.', 1200, 2.50, 'Fast and low noise', 'Easy to install, fix or replace', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(3, 'NA03', 3, '3 x 2 x 3', 'This configuration offers greater travel capabilities because of the telescoping sections. Integate DRA technology of Australia and twisted hardcore techniques of Italy. Instead of one section of plunger to extend up, there are two sections, thus allowing for almost twice the distance in travel being reached. This series is great for any kind of buildings.', 900, 2.50, 'Fast, stable and quiet', 'Small space occupation', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(4, 'SS04', 4, '2.5 x 2.5 x 2.5', 'Manufacturers choose rare earth material and fully combine coaxial transmission technology and digital variable frequency technology with group computer combined control technology. With less running cost & less energy dissipated. Compared with the ordinary and traditional technology, gearless traction machine can save more than 40% of the energy. The motor is developed base on technology of Canada.', 1100, 2.50, 'Fast, save and stable', 'Full safety features available as an option', 'High energy saving', GETDATE(), null)
insert into product_informations values(5, 'TW05', 5, '3 x 2.5 x 3', 'Designed using modern techniques and quiet machine technology of Japanese. This is the first level of above ground jack configuration available, and it features only one section of plunger that extends out of the cylinder. It has automatic control center so it can save more than 35% energy. It is a perfect fit for skyscrapers and malls.', 1100, 2.00, 'Fast and low noise', 'Easy to install, fix or replace', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(6, 'RK06', 6, '3 x 2 x 3', 'This configuration offers greater travel capabilities because of the telescoping sections. Integate DRA technology of Australia and twisted hardcore techniques of Italy. Instead of one section of plunger to extend up, there are two sections, thus allowing for almost twice the distance in travel being reached. This series is great for any kind of buildings.', 950, 2.50, 'Fast, stable and quiet', 'Small space occupation', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(7, 'TBS07', 1, '2 x 2 x 2.5', 'This design can offer you a pleasant ambience, special inverter system control the running position, upgrading the comfort feeling all-sided. Use titanium alloy technology to build, it is super enduring. It not only reduce the cost of designing and building on machine room, also make best use of you building space. Small occupying space, no hoistway, safe and durable elevator is provided for you', 400, 2.00, 'Fast, stable and low noise', 'Easy to install, fix or replace', 'High precision hydraulic drive system', GETDATE(), null)
insert into product_informations values(8, 'NCP08', 2, '2 x 2 x 2.5', 'It applies new generation permanent synchronnous techniques. Integrates Dual-gear technology and VCI system of Japanese. Its advantages of high power factor and zero slip frequency effectively increase the running efficiency, reduces the energy-loss cost, fulfils high energy-saving standard.', 500, 2.00, 'Fast and quiet', 'High safety and high energy saving', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(9, 'FLV09', 3, '2.5 x 2.5 x 2.5', 'Designed for home use. Single phase power supply, low pit depth and overhead requirements, it can be installed without large scale construction modification. Furthermore it is also equipped high configuration, such as gearless traction motor, automatic door opening and VVVF driving system. With the luxury decorations, it is not only a vertical transpiration tool at your home, but also reflect your personal tasty.', 500, 2.00, 'Fast, high safety and quiet', 'Smooth lifting motion', 'Full safety features available as an option', GETDATE(), null)
insert into product_informations values(10, 'GM10', 4, '2 x 2 x 2.5', 'This design can offer you a pleasant ambience, special inverter system control the running position, upgrading the comfort feeling all-sided. Use titanium alloy technology to build, it is super enduring. It not only reduce the cost of designing and building on machine room, also make best use of you building space. Small occupying space, no hoistway, safe and durable elevator is provided for you', 400, 2.00, 'Fast, stable and low noise', 'Easy to install, fix or replace', 'High precision hydraulic drive system', GETDATE(), null)
insert into product_informations values(11, 'OM11', 5, '2 x 2 x 2.5', 'It applies new generation permanent synchronnous techniques. Integrates Dual-gear technology and VCI system of Japanese. Its advantages of high power factor and zero slip frequency effectively increase the running efficiency, reduces the energy-loss cost, fulfils high energy-saving standard.', 500, 2.00, 'Fast and quiet', 'High safety and high energy saving', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(12, 'WD12', 6, '2.5 x 2.5 x 2.5', 'Designed for home use. Single phase power supply, low pit depth and overhead requirements, it can be installed without large scale construction modification. Furthermore it is also equipped high configuration, such as gearless traction motor, automatic door opening and VVVF driving system. With the luxury decorations, it is not only a vertical transpiration tool at your home, but also reflect your personal tasty.', 500, 2.00, 'Fast, high safety and quiet', 'Smooth lifting motion', 'Full safety features available as an option', GETDATE(), null)
insert into product_informations values(13, 'BM13', 1, '4 x 4 x 4', 'Flexible to various places, saving land coverage. The core of the device lays inside with good stability performance. Multiple mechanical and electric safety structure design, high security. Transmission parts and wiring are located inside the equipment, no exposed, high safety performance.', 5000, 2.50, 'Fast, stable and quiet', 'High precision hydraulic drive system', 'High safety and high energy saving', GETDATE(), null)
insert into product_informations values(14, 'GT14', 2, '3.5 x 2.5 x 2', 'The hydraulic cylinders and hoses are armed with explosion-proof lock valve that aims to avoid a sudden drop in case hydraulic pipe breaks, the work platform will drops slowly to ensure car is safety. The handrail guarantees the safety of cars. Equipped with 4 guide rails to ensure safety and steady. The limit switch system ensures lifts stop at accurate position. Overload protection facility will protect the lift from working when goods are overloaded.', 4000, 1.50, 'Fast and low noise', 'Energy saving', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(15, 'WK15', 3, '4 x 3 x 3.5', 'Developed with an account of the domestic market situation. By means of employing highly matured VVVF technology to realize precise speed control, which avoids the noise and vibration of the cabins caused by increased load of the traction system due to the uneven force on the cabin and best optimizes the characteristics of this product series, making it safer and more reliable for the entry of cars. With the floor is made of three layers of steel and the motion has 3 motors, it is super strong.', 6000, 3.00, 'Fast, stable and save', 'High precision hydraulic drive system', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(16, 'MP16', 4, '4 x 4 x 4', 'Flexible to various places, saving land coverage. The core of the device lays inside with good stability performance. Multiple mechanical and electric safety structure design, high security. Transmission parts and wiring are located inside the equipment, no exposed, high safety performance.', 5000, 2.50, 'Fast, stable and quiet', 'High precision hydraulic drive system', 'High safety and high energy saving', GETDATE(), null)
insert into product_informations values(17, 'TH17', 5, '3.5 x 2.5 x 2', 'The hydraulic cylinders and hoses are armed with explosion-proof lock valve that aims to avoid a sudden drop in case hydraulic pipe breaks, the work platform will drops slowly to ensure car is safety. The handrail guarantees the safety of cars. Equipped with 4 guide rails to ensure safety and steady. The limit switch system ensures lifts stop at accurate position. Overload protection facility will protect the lift from working when goods are overloaded.', 4000, 1.50, 'Fast and low noise', 'Energy saving', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(18, 'SM18', 6, '4 x 3 x 3.5', 'Developed with an account of the domestic market situation. By means of employing highly matured VVVF technology to realize precise speed control, which avoids the noise and vibration of the cabins caused by increased load of the traction system due to the uneven force on the cabin and best optimizes the characteristics of this product series, making it safer and more reliable for the entry of cars. With the floor is made of three layers of steel and the motion has 3 motors, it is super strong.', 6000, 3.00, 'Fast, stable and save', 'High precision hydraulic drive system', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(19, 'DS19', 1, '4 x 3 x 3', 'With the precise speed control and the whole closed-loop controlled by VVVF drive system, hospital elevator has high-precision running condition. It can make the stop layer very accurate, start and stop very stable. VVVF inverter control system together with precision control of motor accelerated degrees make traction machine noise smaller. This fully embodies the"people-oriented" design idea, creating a warm and comfortable space for both doctors and patients. It is convenient for beds, wheel chairs and trolleys.', 1000, 2.00, 'Fast and quiet', 'Easy to install, fix or replace', 'Energy saving', GETDATE(), null)
insert into product_informations values(20, 'PL20', 2, '4.5 x 3 x 3', 'Designed to provide safe and convenient transportation. This combination of features ensures optimum lift function effectively meet the needs of hospitals, from transporting patients in his hospital bed to transport medical equipment. This design is suitable with hospitals because of modern techniques and technology.', 900, 3.00, 'Fast, save and stable', 'Full safety features available as an option', 'High energy saving', GETDATE(), null)
insert into product_informations values(21, 'NS21', 3, '4 x 2.5 x 3', 'Designed with modern techniques and technology to bring comfort to patients. With magnetic reversal motion technology, it provide quiet and relax transportation. It is convenient for beds, wheel chairs and trolleys.', 1100, 3.50, 'Fast, stable and low noise', 'Smooth lifting motion', 'High precision hydraulic drive system', GETDATE(), null)
insert into product_informations values(22, 'BH22', 4, '4 x 3 x 3', 'With the precise speed control and the whole closed-loop controlled by VVVF drive system, hospital elevator has high-precision running condition. It can make the stop layer very accurate, start and stop very stable. VVVF inverter control system together with precision control of motor accelerated degrees make traction machine noise smaller. This fully embodies the"people-oriented" design idea, creating a warm and comfortable space for both doctors and patients. It is convenient for beds, wheel chairs and trolleys.', 1000, 2.00, 'Fast and quiet', 'Easy to install, fix or replace', 'Energy saving', GETDATE(), null)
insert into product_informations values(23, 'BB23', 5, '4.5 x 3 x 3', 'Designed to provide safe and convenient transportation. This combination of features ensures optimum lift function effectively meet the needs of hospitals, from transporting patients in his hospital bed to transport medical equipment. This design is suitable with hospitals because of modern techniques and technology.', 900, 3.00, 'Fast, save and stable', 'Full safety features available as an option', 'High energy saving', GETDATE(), null)
insert into product_informations values(24, 'BS24', 6, '4 x 2.5 x 3', 'Designed with modern techniques and technology to bring comfort to patients. With magnetic reversal motion technology, it provide quiet and relax transportation. It is convenient for beds, wheel chairs and trolleys.', 1100, 3.50, 'Fast, stable and low noise', 'Smooth lifting motion', 'High precision hydraulic drive system', GETDATE(), null)
insert into product_informations values(25, 'ES25', 1, '3 x 2 x 3.5', 'It integrates high technology, which enhances the performance and quality of the goods lift, reduces the failure rate. The positive quality of thegoods lift includes rigorous structure, structural durability, large door opening size, safe and smooth operation. It becomes the best choice of goods transporting for enterprisers, such as factory, warehouse, shopping mall, logistic center, library and so on.', 2000, 3.00, 'Fast and low noise', 'High safety and high energy saving', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(26, 'TP26', 2, '2 x 4 x 3', 'Internationally advanced AC frequency conversion and transformation driving system Powerful Wellift modular intelligent control system with serial communication information processing, to realize remote monitoring and management. Stable and reliable frequency conversion and transformation timing door machine system. Safe and smart door infrared ray curtain safety protection device. Solid box structure, well-chosen gilded material and durable calling landing parts.', 3000, 2.00, 'Fast and low noise', 'Full safety features available as an option', 'Energy saving', GETDATE(), null)
insert into product_informations values(27, 'ET27', 3, '3 x 4 x 3.5', 'Designed for heavy duty transportation with dual-cable techniques.It is known as great performance, stable operation, high efficiency with reliable CTRL 80 system which always perform accurately. Made of stainless steel, has longer term of use. Suittable with every kind of buildings.', 3000, 3.00, 'Fast, stable and quiet', 'Smooth lifting motion', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(28, 'LD28', 4, '3 x 2 x 3.5', 'It integrates high technology, which enhances the performance and quality of the goods lift, reduces the failure rate. The positive quality of thegoods lift includes rigorous structure, structural durability, large door opening size, safe and smooth operation. It becomes the best choice of goods transporting for enterprisers, such as factory, warehouse, shopping mall, logistic center, library and so on.', 2000, 3.00, 'Fast and low noise', 'High safety and high energy saving', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(29, 'NW29', 5, '2 x 4 x 3', 'Internationally advanced AC frequency conversion and transformation driving system Powerful Wellift modular intelligent control system with serial communication information processing, to realize remote monitoring and management. Stable and reliable frequency conversion and transformation timing door machine system. Safe and smart door infrared ray curtain safety protection device. Solid box structure, well-chosen gilded material and durable calling landing parts.', 3000, 2.00, 'Fast and low noise', 'Full safety features available as an option', 'Energy saving', GETDATE(), null)
insert into product_informations values(30, 'SB30', 6, '3 x 4 x 3.5', 'Designed for heavy duty transportation with dual-cable techniques.It is known as great performance, stable operation, high efficiency with reliable CTRL 80 system which always perform accurately. Made of stainless steel, has longer term of use. Suittable with every kind of buildings.', 3000, 3.00, 'Fast, stable and quiet', 'Smooth lifting motion', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(31, 'WR31', 1, '1.5 x 1.5 x 4', 'Combined with the study achievement of adaptability between IFE elevator system and architecture. It is completely integrated with building and entirely enhances architecture aesthetics and value. It is just like the eyes of building, which let the architecture character to outpour completely. It has a unique EASYAIR ventilation system, which can pump outside fresh air into the elevator, so makes passengers feel fresh and relaxation.', 800, 2.00, 'Fast, save and stable', 'Small space occupation', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(32, 'DK32', 2, '1.5 x 1.5 x 4', 'The concise and reliable serial communication network is adopted between various control sub-systems in order to fulfill a series of the functions such as calling, command, group supervision signal, floor display etc. It greatly reduces the trouble possibilities out of circuit problem, largely enhances the reliability of the elevator travel. At the same time, it brings more convenient repair and maintenance to the elevator system.', 800, 2.00, 'Fast and quiet', 'Easy to install, fix or replace', 'High safety and high energy saving', GETDATE(), null)
insert into product_informations values(33, 'PA33', 3, '1.5 x 1.5 x 4', 'Transformed from V8 elevator by changing car shape while preserving the complete advanced functions. The installation type can be outdoor close type or indoor open type depending on mounted site. Machine room-less design is available when arrangement planning, so that it is applicable for both new-type well in new building and old-type well or reconstructed well in old building.', 800, 2.50, 'Fast, stable and low noise', 'High safety and high energy saving', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(34, 'DP34', 4, '1.5 x 1.5 x 4', 'Combined with the study achievement of adaptability between IFE elevator system and architecture. It is completely integrated with building and entirely enhances architecture aesthetics and value. It is just like the eyes of building, which let the architecture character to outpour completely. It has a unique EASYAIR ventilation system, which can pump outside fresh air into the elevator, so makes passengers feel fresh and relaxation.', 800, 2.00, 'Fast, save and stable', 'Small space occupation', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(35, 'SD35', 5, '1.5 x 1.5 x 4', 'The concise and reliable serial communication network is adopted between various control sub-systems in order to fulfill a series of the functions such as calling, command, group supervision signal, floor display etc. It greatly reduces the trouble possibilities out of circuit problem, largely enhances the reliability of the elevator travel. At the same time, it brings more convenient repair and maintenance to the elevator system.', 800, 2.00, 'Fast and quiet', 'Easy to install, fix or replace', 'High safety and high energy saving', GETDATE(), null)
insert into product_informations values(36, 'AA36', 6, '1.5 x 1.5 x 4', 'Transformed from V8 elevator by changing car shape while preserving the complete advanced functions. The installation type can be outdoor close type or indoor open type depending on mounted site. Machine room-less design is available when arrangement planning, so that it is applicable for both new-type well in new building and old-type well or reconstructed well in old building.', 800, 2.50, 'Fast, stable and low noise', 'High safety and high energy saving', 'Build-in balance system', GETDATE(), null)
insert into product_informations values(37, 'POTM37', 1, '1.5 x 1 x 1.5', 'It applies new generation permanent synchronnous techniques. Single phase power supply, low pit depth and overhead requirements, it can be installed without large scale construction modification. Gearless traction machine needs no renewal of lubricant.', 200, 2.00, 'Fast, save and stable', 'Small space occupation', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(38, 'MR38', 2, '1 x 1 x 1.5', 'Designed for home use or restaurant with tasteful traditional style. Single phase power supply, low pit depth and overhead requirements, it can be installed without large scale construction modification. Furthermore it is also equipped high configuration, such as gearless traction motor, automatic door opening and VVVF driving system. With the luxury decorations, it is not only a vertical transpiration tool at your home, but also reflect your personal tasty.', 200, 2.00, 'Fast and low noise', 'Full safety features available as an option', 'Energy saving', GETDATE(), null)
insert into product_informations values(39, 'LS39', 3, '1.5 x 1.5 x 1.5', 'Developed with techniques of German, modern style with stainless steel. Integrate Dual-gear technology and VCI system of Japanese. Compared with the ordinary and traditional technology, gearless traction machine can save more than 30% of the energy.', 250, 2.00, 'Fast, stable and quiet', 'Easy to install, fix or replace', 'High safety and high energy saving', GETDATE(), null)
insert into product_informations values(40, 'TA40', 4, '1.5 x 1 x 1.5', 'It applies new generation permanent synchronnous techniques. Single phase power supply, low pit depth and overhead requirements, it can be installed without large scale construction modification. Gearless traction machine needs no renewal of lubricant.', 200, 2.00, 'Fast, save and stable', 'Small space occupation', 'Smooth lifting motion', GETDATE(), null)
insert into product_informations values(41, 'QOP41', 5, '1 x 1 x 1.5', 'Designed for home use or restaurant with tasteful traditional style. Single phase power supply, low pit depth and overhead requirements, it can be installed without large scale construction modification. Furthermore it is also equipped high configuration, such as gearless traction motor, automatic door opening and VVVF driving system. With the luxury decorations, it is not only a vertical transpiration tool at your home, but also reflect your personal tasty.', 200, 2.00, 'Fast and low noise', 'Full safety features available as an option', 'Energy saving', GETDATE(), null)
insert into product_informations values(42, 'OD42', 6, '1.5 x 1.5 x 1.5', 'Developed with techniques of German, modern style with stainless steel. Integrate Dual-gear technology and VCI system of Japanese. Compared with the ordinary and traditional technology, gearless traction machine can save more than 30% of the energy.', 250, 2.00, 'Fast, stable and quiet', 'Easy to install, fix or replace', 'High safety and high energy saving', GETDATE(), null)

insert into payment_types values('Cash')
insert into payment_types values('Bank Transfer')

insert into list_status values('Pending')
insert into list_status values('Proccess')
insert into list_status values('Completed')

insert into feedback_level values('Good')
insert into feedback_level values('Average')
insert into feedback_level values('Satisfactory')
insert into feedback_level values('Poor')
