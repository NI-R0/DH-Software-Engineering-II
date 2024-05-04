create table if not exists  Institution(
    name varchar(20) primary key,
    type enum('BANK','BROKER') not null
    );

create table if not exists  Account(
    accountId uuid primary key,
    institution varchar(20),
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
    account uuid,
    description varchar(255),
    type enum('INCOME','EXPENSE','BUY','SELL'),
    amount float not null,
    unit varchar(10) not null,
    timestamp timestamp not null,
    foreign key (account) references Account(accountId)
    );


insert into Institution(name, type) values('Volksbank', 'BANK');
insert into Institution(name, type) values('Sparkasse', 'BANK');
insert into Institution(name, type) values('ING DIBA', 'BANK');

insert into Institution(name, type) values('Binance', 'BROKER');
insert into Institution(name, type) values('Flatex', 'BROKER');
insert into Institution(name, type) values('eToro', 'BROKER');