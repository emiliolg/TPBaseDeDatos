
use biblioteca;

#
#   Contiene los datos de un libro
#   (De cada libro la biblioteca puede tener 0 a n ejemplares, lo cual se maneja en la tabla de ejemplares
#
create table libros
(
    id                   serial     primary key,
    titulo               varchar(100) not null,
    autor                varchar(50),
    editorial            varchar(50),
    isbn                 numeric(13)  unique,
    fecha_pub            date
)
comment 'Tabla de Libros';

delimiter $$

create procedure alta_libro(
    tituloLibro varchar(100),
    autorLibro  varchar(50),
    editorLibro varchar(50),
    isbnLibro   numeric(13),
    fechaPub    date
)
comment 'Da de Alta un Libro'
begin
     insert into libros(titulo, autor, editorial, isbn, fecha_pub)
            values (tituloLibro, autorLibro, editorLibro, isbnLibro, fechaPub);
end $$

create procedure mod_libro(
      idLibro     bigint,
      tituloLibro varchar(100),
      autorLibro  varchar(50),
      editorLibro varchar(50),
      isbnLibro   numeric(13),
      fechaPub    date
)
comment 'Modifica los datos básicos de un Libro'
begin
     update libros
     set titulo = ifnull(tituloLibro, titulo),
          autor = ifnull(autorLibro, autor),
          editorial = ifnull(editorLibro, editorial),
          isbn = ifnull(isbnLibro, isbn),
          fecha_pub = ifnull(fechaPub, fecha_pub)
     where id = idLibro;
end $$

delimiter ;
