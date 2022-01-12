package DAO;

import java.sql.ResultSet;

public interface IDAO<T> {
    ResultSet find(String id);
    ResultSet getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}
