
use biblioteca;


#
# Procedimiento para retornar un libro
#

delimiter $$

create procedure retornar_libro(idLibro bigint, dniSocio int)
    comment 'Retorna el libro especificado'
begin
    # Verificar si lo tenia reservado
    if not exists(select * from ejemplares where id_libro = idLibro and dni_socio = dniSocio) then
        signal sqlstate '45005' set message_text = 'Codigo de libro o dni incorrecto!';
        # Verificar que existe el socio y esta activo
    else
        update ejemplares
        set fecha_reserva = null,
            fecha_retorno = null,
            dni_socio = null
        where id_libro = idLibro and dni_socio = dniSocio
    end if;
end $$

delimiter ;
