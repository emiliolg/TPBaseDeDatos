
#
# Empleados contiene los datos de los empleados de la biblioteca
# Se crean ademas dos procedures para dar de alta o modificar un empleado
#

use biblioteca;

create table empleados
(
    dni               int         not null primary key unique,
    nombre            varchar(30) not null,
    apellido          varchar(30) not null,
    fecha_nacimiento  date        not null,
    turno             enum('Manana', 'Tarde', 'Noche') not null

)
comment 'Tabla de Empleados';

delimiter $$

create function es_menor(fechaNacimiento date) returns boolean
    return timestampdiff(year, fechaNacimiento, curdate()) < 18
$$

create procedure alta_empleado (d int, n varchar(30), a varchar(30), f date, t varchar(10))
comment 'Da de Alta un Empleado'
begin

    if es_menor(f) then
        signal sqlstate '45000' set message_text = 'Debe ser mayor de edad!';
    else
        insert into biblioteca.empleados
               (dni, nombre, apellido, fecha_nacimiento, turno)
        values (d, n, a, f, t);
   end if;
end $$

create procedure mod_empleado (d int, n varchar(30), a varchar(30), f date, t varchar(10))
comment 'Modifica los datos de un Empleado'
begin
    if es_menor(f) then
        signal sqlstate '45000' set message_text = 'Debe ser mayor de edad!';
    else
        update biblioteca.empleados
            set nombre = ifnull(n, nombre),
                apellido = ifnull(a, apellido),
                fecha_nacimiento = ifnull(f, fecha_nacimiento),
                turno = ifnull(t, turno)
        where dni = d;
    end if;
end $$

delimiter ;
