create table destinatario(
	id int not null auto_increment,
    nome varchar(60) not null,
    logradouro varchar(255) not null,
    numero varchar(30) not null,
    complemento varchar(60),
    bairro varchar(30) not null,

    primary key (id)
);