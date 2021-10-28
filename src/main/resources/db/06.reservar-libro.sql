
use biblioteca;


#
# Procedimiento para reservar un libro
#

delimiter $$

create procedure reservar_libro(idLibro bigint, dniSocio int, fechaRetorno date)
    comment 'Reserva el ejemplar del libro especificado si hay un ejemplar disponible'
begin
    # Verificar si existe ese libro
    if not exists(select * from libros where id = idLibro) then
        signal sqlstate '45001' set message_text = 'Libro inexistente!';
        # Verificar que existe el socio y esta activo
    elseif not exists(select * from socios where dni = dniSocio and activo = 1) then
        signal sqlstate '45002' set message_text = 'Socio inexistente o no activo!';
        # Verificar si el socio ya tiene reservado el mismo libro
    elseif exists(select * from ejemplares where id_libro = idLibro and dni_socio = dniSocio) then
        signal sqlstate '45003' set message_text = 'El socio ya reservo ese libro!';
        # Verificar disponibilidad
    elseif not exists(select * from ejemplares where id_libro = idLibro and dni_socio is null) then
        signal sqlstate '45004' set message_text = 'No hay Ejemplares disponibles!';
    else
        update ejemplares
        set fecha_reserva = curdate(),
            fecha_retorno = ifnull(fechaRetorno, timestampadd(day, 1, curdate())),
            dni_socio = dniSocio
        where id_libro = idLibro and dni_socio is null
        limit 1;
        select * from ejemplares where id_libro = idLibro and dni_socio = dniSocio ;
    end if;
end $$

delimiter ;
