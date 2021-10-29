
use biblioteca;

#
#  Contiene los ejemplares de cada libro y su estado (En biblioteca, prestados, etc)
#
create table ejemplares
(
    id                   serial     primary key,
    id_libro             bigint unsigned not null,
    fecha_alta           date not null,
    fecha_reserva        date,
    fecha_retorno        date,
    dni_socio            int,
    foreign key (id_libro) references libros(id),
    foreign key (dni_socio) references socios(dni)

)
comment 'Tabla de Ejemplares de cada libro';

delimiter $$

create procedure alta_ejemplar(idLibro bigint)
    comment 'Da de Alta un Ejemplar de un libro, retorna el id del nuevo ejemplar'
begin
    insert into ejemplares(id_libro, fecha_alta) values (idLibro, curdate());
    select last_insert_id() as Identificador;
end $$

create procedure reservar_libro(idLibro bigint, dniSocio int)
    comment 'Reserva el ejemplar del libro especificado si hay un ejemplar disponible'
begin

end $$

delimiter ;
