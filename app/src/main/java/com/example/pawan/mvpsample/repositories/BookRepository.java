package com.example.pawan.mvpsample.repositories;

import com.example.pawan.mvpsample.book.models.Book;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Pawan on 26/01/18.
 */

public interface BookRepository {
    List<Book> getBooks();
    Single<List<Book>> getBooksReactively();
}
