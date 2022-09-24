use ep01_joaquinmoreno


create table  Books(

	id int,
	book_title varchar(255),
	category varchar(255),
	description varchar(255),
	price double

)


alter table Books add primary key(id);

ALTER TABLE ep01_joaquinmoreno.Books MODIFY COLUMN id int auto_increment NOT NULL;


/*insert Data */
insert into Books (book_title,
category,
description,
price) values ('Secrets of Silicon Valley','popular_comp',
'Muckraking reporting on the world''s largest computer hardware and software manufacturers.', 19.99);

insert into Books (book_title,
category,
description,
price) values ('The Busy Executive''s Database Guide','business',
'An overview of available database systems with emphasis on common business applications. Illustrated.', 12.99);

insert into Books (book_title,
category,
description,
price) values ('Emotional Security: A New Algorithm','psychology',
'Protecting yourself and your loved ones from undue emotional stress in the modern world. Use of computer and nutritional aids emphasized.', 2.99);

insert into Books (book_title,
category,
description,
price) values ('Cooking with Computers: Surreptitious Balance Sheets','business',
'Helpful hints on how to use your electronic resources to the best advantage.', 11.95);





select * from books b

create procedure usp_addBook(in b_name VARCHAR(255),in b_cat VARCHAR(255), in b_desc VARCHAR(255), in b_price Double)

     begin
     		insert into Books (book_title,category,description,price) values (b_name,b_cat,b_desc, b_price);

     end

--    call usp_addBook("Sushi, Anyone?","trad_cook", "Detailed instructions on how to make authentic Japanese sushi in your spare time.", 14.99);



     create procedure usp_listBook(in p_name VARCHAR(255))

     begin
     		select * from books b  where book_title =p_name;
     end


--  	call usp_listBook("But Is It User Friendly?")