package com.example.pawan.mvpsample.book.models;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Pawan on 27/01/18.
 */

public class CustomDao<T, ID> extends BaseDaoImpl<T, ID> {
    protected CustomDao(final Class<T> dataClass) throws SQLException {
        super(dataClass);
    }

    public CustomDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }


    @Override
    public int create(final T data) throws SQLException {
        int result = super.create(data);
        // Send an event with EventBus or Otto
        return result;
    }
}
