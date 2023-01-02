drop table if exists SAMPLE;

create table SAMPLE(
    id   varchar(255) not null,
    description varchar(255) ,
    primary key (id)
);

insert into SAMPLE values ('12345', '샘플 데이터');
