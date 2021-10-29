use biblioteca;

#
# Tabla que guarda la historia de todas las reservas por libro
# Se actualiza a través de un trigger en la tabla de ejemplares
#

create table historial
(
    id            serial primary key,
    idLibro       bigint unsigned not null,
    dniSocio      int             not null,
    fecha_reserva date            not null,
    fecha_retorno date            not null,

    foreign key (idLibro) references libros (id),
    foreign key (dniSocio) references socios (dni)
);

delimiter $$

create trigger registrar_retorno
after update
on ejemplares for each row
begin
    if old.dni_socio is not null and new.dni_socio is null then
        insert into historial
            (idLibro, dniSocio, fecha_reserva, fecha_retorno)
            values (old.id_libro, old.dni_socio, old.fecha_reserva, curdate());
    end if;
end $$

delimiter ;
