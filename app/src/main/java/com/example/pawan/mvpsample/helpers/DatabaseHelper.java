package com.example.pawan.mvpsample.helpers;

import android.content.Context;
import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.pawan.mvpsample.book.models.Book;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "ormlite.db";
    private static final int    DATABASE_VERSION = 10;

    private Dao<Book, Integer> mBookDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Book.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Book.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /* Book */

    public Dao<Book, Integer> getBookDao() throws SQLException {
        if (mBookDao == null) {
            mBookDao = getDao(Book.class);
        }

        return mBookDao;
    }

    @Override
    public void close() {
        mBookDao = null;

        super.close();
    }
}


