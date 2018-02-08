package com.example.pawan.mvpsample.book.views;

import com.example.pawan.mvpsample.book.models.Book;

import java.util.List;

/**
 * Created by Pawan on 26/01/18.
 */

public interface BookListFragmentView {
    void displayBooks(List<Book> list);
    void displayNoBooks();
    void displayError();
}
