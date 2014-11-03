USE library;CREATE TABLE BOOK (isbn char(10) not null, title varchar(100) not null,CONSTRAINT pk_book PRIMARY KEY(isbn));CREATE TABLE AUTHOR (isbn char(10) not null,author_name varchar(100) not null,type int,role varchar(11),CONSTRAINT pk_author PRIMARY KEY(isbn, author_name),CONSTRAINT fk_author_book FOREIGN KEY(isbn) REFERENCES BOOK(isbn));CREATE TABLE BRANCH (branch_id int not null,branch_name varchar(100) not null,address varchar(100),CONSTRAINT pk_branch PRIMARY KEY(branch_id));CREATE TABLE BOOK_COPIES (isbn char(10) not null,branch_id int not null,num_of_copies int not null,available_copies int not null, CONSTRAINT pk_book_copies PRIMARY KEY(isbn, branch_id),CONSTRAINT fk_book_copies_book FOREIGN KEY(isbn) REFERENCES BOOK(isbn),CONSTRAINT fk_book_copies_branch FOREIGN KEY(branch_id) REFERENCES BRANCH(branch_id));CREATE TABLE BORROWER (card_no int not null,fname varchar(100),lname varchar(100),address varchar(100), city varchar(25), state varchar(5), phone char(14),CONSTRAINT pk_borrower PRIMARY KEY(card_no));CREATE TABLE BOOK_LOANS (loan_id int not null AUTO_INCREMENT,isbn char(10) not null,branch_id int not null,card_no char(10) not  null,date_out date not null,due_date date,date_in date,CONSTRAINT pk_book_loans PRIMARY KEY(loan_id),CONSTRAINT fk_book_loans_book FOREIGN KEY(isbn) REFERENCES BOOK(isbn),CONSTRAINT fk_book_loans_branch FOREIGN KEY(branch_id) REFERENCES BRANCH(branch_id),CONSTRAINT fk_book_loans_borrower FOREIGN KEY(card_no) REFERENCES BORROWER(card_no));CREATE TABLE FINES (loan_id int not null,fine_amt decimal(5,2) not null,paid boolean not null,CONSTRAINT pk_fines PRIMARY KEY(loan_id),CONSTRAINT fk_fines_book_loans FOREIGN KEY(loan_id) REFERENCES BOOK_LOANS(loan_id));