insert into product(product_id, product_name , sku , created) values( 1001  , 'test product1' , 'abc123' , now());
insert into product(product_id, product_name , sku , created) values( 1002  , 'test product2' , 'xyz123' , now());

insert into category(category_id , category_name , created) values( 8888, 'test category1' ,  now());
insert into category(category_id , category_name , created) values(9999, 'test category2' ,  now());

insert into catprdrel(category_id , product_id , created) values(9999, 1001 ,  now());
insert into catprdrel(category_id , product_id , created) values(8888, 1002 ,  now());

insert into productprice values( 991, 1002  , 59.99, 39.99, 9.97,  now() , now());
insert into productprice values( 992, 1001  , 559.99, 339.99, 99.97,  now() , now());
