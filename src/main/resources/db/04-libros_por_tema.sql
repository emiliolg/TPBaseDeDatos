
use biblioteca;

#
#   Contiene la asociación entre temas y libros
#
create table temas
(
    id                   serial     primary key,
    tema                 varchar(20) not null,
    desc_tema            varchar(1000) -- Una descripción completa de lo que trata ese tema
);

create table libros_por_tema
(
    id_tema    bigint unsigned not null,
    id_libro   bigint unsigned not null,
    primary key (id_tema, id_libro),
    foreign key (id_libro) references libros(id) on delete cascade,
    foreign key (id_tema) references temas(id)
);

delimiter $$

create procedure agregar_tema(idLibro bigint, temaLibro varchar(20))
begin
    declare idTema bigint unsigned;
    set idTema = (select id from temas where tema = temaLibro);
    if idTema is null then
        insert into temas(tema, desc_tema) values (temaLibro, '');
        set idTema = LAST_INSERT_ID();
    end if;
    insert ignore into libros_por_tema
    set id_tema = idTema,
        id_libro = idLibro;
end $$

create procedure borrar_tema(idLibro bigint, temaLibro varchar(20))
begin
    declare idTema bigint unsigned;
    set idTema = (select id from temas where tema = temaLibro);
    if idTema is not null then
        delete from libros_por_tema where id_tema = idTema and id_libro = idLibro;
    end if;
end $$


create procedure listar_libros_por_tema(temaLibro varchar(20))
begin
    select *
    from libros
    where id in (
        select id_libro
        from libros_por_tema
        where id_tema in (select id from temas where tema = temaLibro)
    );
end $$

delimiter ;
