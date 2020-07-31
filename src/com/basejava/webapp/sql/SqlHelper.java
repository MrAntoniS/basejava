package com.basejava.webapp.sql;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void toDo(String sqlRequest, Maker maker) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlRequest)) {
            maker.make(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface Maker {
        void make(PreparedStatement ps) throws SQLException;
    }

    public <T> T toReturn(String sqlRequest, Returner<T> returner) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlRequest)) {
            return returner.toReturn(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    public interface Returner<T> {
        T toReturn(PreparedStatement ps) throws SQLException;
    }
}
