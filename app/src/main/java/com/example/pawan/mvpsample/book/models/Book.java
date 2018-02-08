package com.example.pawan.mvpsample.book.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Pawan on 26/01/18.
 */

@DatabaseTable(tableName = Book.TABLE_NAME, daoClass = CustomDao.class)
public class Book {
    public static final String TABLE_NAME = "books";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_TITLE = "title";
    private static final String FIELD_NAME_AUTHOR = "author";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_TITLE)
    private String title;

    @DatabaseField(columnName = FIELD_NAME_AUTHOR)
    private String author;

    public Book() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
