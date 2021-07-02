CREATE TABLE transaction(id_transaction bigint, devise_src varchar, devise_dest varchar,montant int,date_cotation TIMESTAMP, taux decimal);
insert into transaction (id_transaction,devise_src,devise_dest,montant,date_cotation,taux)
values(10001,'EUR','USD',1000,'2021-05-21',1.17);
insert into transaction (id_transaction,devise_src,devise_dest,montant,date_cotation,taux)
values(10002,'EUR','JPY',1000,'2021-05-21',130);
insert into transaction (id_transaction,devise_src,devise_dest,montant,date_cotation,taux)
values(10003,'USD','GBP',900,'2021-05-21',0.75);
insert into transaction (id_transaction,devise_src,devise_dest,montant,date_cotation,taux)
values(10004,'EUR','USD',750,'2021-05-20',1.18);
