package com.example.pawan.mvpsample;

import com.example.pawan.mvpsample.book.models.Book;
import com.example.pawan.mvpsample.book.presenters.BookListFragmentPresenter;
import com.example.pawan.mvpsample.repositories.BookRepository;
import com.example.pawan.mvpsample.book.views.BookListFragmentView;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Pawan on 26/01/18.
 */
public class BookListFragmentPresenterTestWithoutMockito {

    @Test
    public void shouldPassBooksToView() {

        // given
        BookListFragmentView view = new MockView();
        BookRepository bookRepository = new MockBookRepository(true);

        // when
        BookListFragmentPresenter presenter = new BookListFragmentPresenter(view, bookRepository);
        presenter.loadBooks();

        // then
        Assert.assertEquals(true, ((MockView) view).displayBooksWithBooks);
    }

    @Test
    public void shouldHandleNoBooksFound() {
        // given
        BookListFragmentView view = new MockView();
        BookRepository bookRepository = new MockBookRepository(false);

        //when
        BookListFragmentPresenter presenter = new BookListFragmentPresenter(view, bookRepository);
        presenter.loadBooks();

        //then
        Assert.assertEquals(true, ((MockView) view).displayBooksWithNoBooks);

    }

    private class MockView implements BookListFragmentView {

        boolean displayBooksWithBooks;
        boolean displayBooksWithNoBooks;

        @Override
        public void displayBooks(List<Book> list) {
            displayBooksWithBooks = list.size() == 3 ? true : false;
        }

        @Override
        public void displayNoBooks() {
            displayBooksWithNoBooks = true;
        }

        @Override
        public void displayError() {

        }
    }

    private class MockBookRepository implements BookRepository {
        private boolean returnBook;

        public MockBookRepository(boolean returnBook) {
            this.returnBook = returnBook;
        }

        @Override
        public List<Book> getBooks() {
            if (returnBook) {
                return Arrays.asList(new Book(), new Book(), new Book());
            } else {
                return Collections.emptyList();
            }

        }

        @Override
        public Single<List<Book>> getBooksReactively() {
            return null;
        }
    }
}
