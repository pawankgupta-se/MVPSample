package com.example.pawan.mvpsample.book.presenters;

import com.example.pawan.mvpsample.book.models.Book;
import com.example.pawan.mvpsample.repositories.BookRepository;
import com.example.pawan.mvpsample.book.views.BookListFragmentView;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Pawan on 26/01/18.
 */

public class BookListFragmentPresenter {
    private BookListFragmentView view;
    private BookRepository bookRepository;
    private CompositeDisposable disposable = new CompositeDisposable();

    public BookListFragmentPresenter(BookListFragmentView view, BookRepository bookRepository) {
        this.view = view;
        this.bookRepository = bookRepository;
    }

    public void loadBooks(int i) {
        bookRepository.getBooksReactively().subscribe(books -> {
            if (books.isEmpty()) {
                view.displayNoBooks();
            } else {
                view.displayBooks(books);
            }
        }, throwable -> view.displayError());

    }

    public void loadBooks() {
        disposable.add(bookRepository.getBooksReactively().subscribeWith(new DisposableSingleObserver<List<Book>>() {
            @Override
            public void onSuccess(List<Book> books) {
                if (books.isEmpty()) {
                    view.displayNoBooks();
                } else {
                    view.displayBooks(books);
                }
            }

            @Override
            public void onError(Throwable e) {
                view.displayError();
            }
        }));

    }

    public void unSubscribe() {
        disposable.clear();
    }
}
