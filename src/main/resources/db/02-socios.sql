
use biblioteca;

/*
  Socios contiene los datos de los soicos de la biblioteca
  Se crean ademas dos procedures para dar de alta o modificar un socio
*/
create table socios
(
    dni               int         not null primary key unique,
    nombre            varchar(30) not null,
    apellido          varchar(30) not null,
    fecha_nacimiento  date        not null,
    socio_desde       date        not null,
    activo            boolean     not null default true
)
comment 'Tabla de Socios';

delimiter $$

create procedure alta_socio (d int, n varchar(30), a varchar(30), f date)
comment 'Da de Alta un Socio'
begin
     insert into socios(dni, nombre, apellido, fecha_nacimiento, socio_desde, activo)
        values (d, n, a, f, curdate(), true);
end $$

create procedure mod_socio (d int, n varchar(30), a varchar(30), f date)
comment 'Modifica los datos básicos de un Socio'
begin
    update socios
    set nombre = n,
        apellido = a,
        fecha_nacimiento = f
    where dni = d;
end $$

delimiter ;
