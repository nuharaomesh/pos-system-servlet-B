package lk.ijse.possystemb.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    List<T> get();
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(String id);
}
