
use biblioteca;

/*
  Contiene los datos de las mensualidades de los socios y su pago
*/
create table mensualidades
(
    dni               int         not null,
    mes               int         not null,
    anio              int         not null,
    monto             numeric     not null,
    fecha_pago        date
)
comment 'Tabla de Mensualidades';

delimiter $$

create procedure generar_mensualidades(cuotaActual numeric)
comment 'Genera las mensualidades para el mes corriente'
begin
     insert into mensualidades(dni, mes, anio, monto)
        select dni,  month(curdate()), year(curdate()), cuotaActual
        from socios
        where activo = 1;
end $$

create procedure pagar_mensualidad(dniSocio int, m int, a int)
    comment 'Registra el paga de la mensualidades del socio indicado'
begin
    if not exists(select * from mensualidades where dni = dniSocio and mes = m and a = anio and fecha_pago is null) then
        signal sqlstate '45030' set message_text = 'Mensualidad no adeudada!';
    else
        update mensualidades
            set fecha_pago = curdate()
        where dni = dniSocio and mes = m and a = anio;
    end if;
end $$

create procedure deuda_por_socio(dniSocio int)
    comment 'Total de deuda para un socio'
begin
    select sum(monto) from mensualidades where fecha_pago is null and dni = dniSocio
end $$

create procedure socios_con_deuda()
    comment 'Socios con deudas'
begin
    select dni, sum(monto) from mensualidades where fecha_pago is null group by dni;
end $$

delimiter ;


