package sql.tables;

import java.sql.SQLException;
import java.util.Set;

public interface Table<T> {
    boolean put(T t) throws SQLException;
    boolean remove(T t) throws SQLException;
    boolean addAll(Set<T> set) throws SQLException;
    Set<T> set() throws SQLException;
    boolean exists(T t) throws SQLException;
}
