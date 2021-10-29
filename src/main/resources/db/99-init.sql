use biblioteca;

-- insert an admin user
insert into user (user_name, password, admin, dni)
values ('juanma', 'password', true, 0);




call alta_empleado(1, 'Homero', 'Simpsons', '1990-10-22', 'Noche');
call alta_empleado(2, 'Marge', 'Simpsons', '1991-10-01', 'Noche');
call mod_empleado(2, 'Marge', 'Simpsons', '1991-10-01', 'Tarde');
call alta_libro('The C Programming Language', 'autor', 'editor', 10, 12/12/12)



