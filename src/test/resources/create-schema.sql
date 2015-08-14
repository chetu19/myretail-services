-- Creation of myretaildb
create SCHEMA myretaildb;

-- USE myretaildb
USE myretaildb;
--------------------------------------------------------
-- table : category : To maintain category information--
--------------------------------------------------------
create table if not exists category (
    category_id smallint /*unsigned*/ not null auto_increment,
    category_name varchar(25) not null,
    created timestamp   /* not null default '0000-00-00 00:00:00'*/,
    updated timestamp /* not null default current_timestamp on update current_timestamp*/,
    primary key (category_id),
    constraint uc_category unique (category_name)
);

--------------------------------------------------------
-- table : product  : To maintain product information---
--------------------------------------------------------
create table if not exists  product(
	product_id                   mediumint /*unsigned*/  not null auto_increment,
	product_name                 varchar(30) not null,
	sku							 varchar(15) not null,
	created                      timestamp  /* not null default '0000-00-00 00:00:00'*/, 
	updated                      timestamp /* not null default current_timestamp on update current_timestamp*/,
	primary key (product_id) ,
	constraint  uc_sku unique (sku)
);
    
--------------------------------------------------------------------------
-- table : product  : To maintain product category product relationship---
--------------------------------------------------------------------------

create table if not exists  catprdrel(
	catprdrel_id                 mediumint /*unsigned*/  not null auto_increment,
	category_id                  smallint  /*unsigned*/   not null,
    product_id                   mediumint /*unsigned*/  not null,
	created                      timestamp /* not null default '0000-00-00 00:00:00'*/, 
	updated                      timestamp /* not null default current_timestamp on update current_timestamp*/,
	primary key (catprdrel_id) ,
    constraint  uc_catprdrel unique (category_id, product_id),
    foreign key (category_id) references category(category_id) on delete cascade on update cascade,
    foreign key (product_id) references product(product_id) on delete cascade on update cascade);
    

--------------------------------------------------------
-- table : Price  : To maintain Product Price information---
--------------------------------------------------------
create table if not exists  productprice(
	
	price_id                     mediumint /*unsigned*/  not null auto_increment,
    product_id                   mediumint /*unsigned*/  not null ,
    regular_price			     decimal(8,2) not null default 0.00,
    sale_price					 decimal(8,2) not null default 0.00,
    clearance_price			     decimal(8,2) not null default 0.00,
	created                      timestamp /* not null default '0000-00-00 00:00:00'*/, 
	updated                      timestamp /* not null default current_timestamp on update current_timestamp*/,
	primary key (price_id) ,
    constraint  uc_product_id unique (product_id),
	foreign key (product_id) references product(product_id) on delete cascade on update cascade
);