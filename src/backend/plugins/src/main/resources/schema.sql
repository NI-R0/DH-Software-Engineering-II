--create table if not exists Users(
--    userId bigint primary key auto_increment,
--    username varchar(15) unique not null
--);

create table if not exists  Institution(
   institutionId uuid primary key,
   name varchar(20) unique not null,
   type enum('BANK','BROKER') not null
);

create table if not exists  Account(
    accountId uuid primary key,
    institution varchar(20) not null,
    accountName varchar(20) not null,
    ownerFirstName varchar(15) not null,
    ownerLastName varchar(25) not null,
    balance double not null,

    --userId bigint not null,
   foreign key (institution) references Institution(name)
    --foreign key (institutionName) references Institution(name)
    --foreign key (userId) references Users(userId)
);

create table if not exists  Transaction(
   transactionId uuid primary key,
   accountId uuid not null,
   description varchar(255),
   type enum('INCOME','EXPENSE','BUY','SELL'),
   amount float not null,
   unit varchar(10) not null,
   timestamp timestamp not null,
   foreign key (accountId) references Account(accountId)
);


insert into Institution(institutionId, name, type) values(RANDOM_UUID(),'Volksbank', 'BANK');
insert into Institution(institutionId, name, type) values(RANDOM_UUID(),'Sparkasse', 'BANK');
insert into Institution(institutionId, name, type) values(RANDOM_UUID(),'ING DIBA', 'BANK');

insert into Institution(institutionId, name, type) values(RANDOM_UUID(),'Binance', 'BROKER');
insert into Institution(institutionId, name, type) values(RANDOM_UUID(),'Flatex', 'BROKER');
insert into Institution(institutionId, name, type) values(RANDOM_UUID(),'eToro', 'BROKER');