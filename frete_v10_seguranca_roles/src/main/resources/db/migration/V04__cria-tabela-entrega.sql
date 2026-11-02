create table entrega(
	id int not null auto_increment,
    cliente_id int not null,
    destinatario_id int not null,
    taxa decimal(10,2) not null,
    status varchar(20) not null,
    data_pedido datetime not null,
    data_finalizacao datetime,

    primary key (id)
);

alter table entrega add constraint fk_entrega_cliente
      foreign key (cliente_id) references cliente (id);

alter table entrega add constraint fk_entrega_destinatario
      foreign key (destinatario_id) references destinatario (id);