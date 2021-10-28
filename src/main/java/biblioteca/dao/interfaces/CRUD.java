package biblioteca.dao.interfaces;

import java.util.List;

//INTERFAZ GENERAL PARA CRUD EN DB
public interface CRUD<T, PK> {

    boolean insert(T newEntity);

    boolean update(PK id, T updatedEntity);

    boolean delete(PK id);

    T find(PK id);

    List<T> list();

}
