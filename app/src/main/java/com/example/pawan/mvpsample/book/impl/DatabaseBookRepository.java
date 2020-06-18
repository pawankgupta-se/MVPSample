package com.example.pawan.mvpsample.book.impl;

import android.content.Context;

import com.example.pawan.mvpsample.helpers.DatabaseHelper;
import com.example.pawan.mvpsample.book.models.Book;
import com.example.pawan.mvpsample.repositories.BookRepository;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Created by Pawan on 27/01/18.
 */

public class DatabaseBookRepository implements BookRepository {
    private DatabaseHelper databaseHelper;

    public DatabaseBookRepository(Context context) {
        this.databaseHelper = OpenHelperManager.getHelper(context,DatabaseHelper.class);
    }



    @Override
    public List<Book> getBooks() {
        try {
          return   databaseHelper.getBookDao().queryBuilder().orderBy("title", true).query();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong !");
        }

    }

    @Override
    public Single<List<Book>> getBooksReactively() {
        return Single.fromCallable(() -> getBooks());
    }
}
