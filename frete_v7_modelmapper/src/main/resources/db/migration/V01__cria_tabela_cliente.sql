    create table cliente (
        id integer not null auto_increment,
        nome varchar(255) not null,
        endereco varchar(255),
        telefone varchar(20) not null,
        primary key (id)
    )