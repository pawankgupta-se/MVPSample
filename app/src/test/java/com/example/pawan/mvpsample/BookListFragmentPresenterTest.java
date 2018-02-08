package com.example.pawan.mvpsample;

import com.example.pawan.mvpsample.book.models.Book;
import com.example.pawan.mvpsample.book.presenters.BookListFragmentPresenter;
import com.example.pawan.mvpsample.repositories.BookRepository;
import com.example.pawan.mvpsample.book.views.BookListFragmentView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Pawan on 26/01/18.
 */

public class BookListFragmentPresenterTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    BookRepository bookRepository;


    @Mock
    BookListFragmentView view;
    BookListFragmentPresenter presenter;
    private final List<Book> MANY_BOOKS = Arrays.asList(new Book(), new Book(), new Book());


    @Before
    public void setUp() throws Exception {
        presenter = new BookListFragmentPresenter(view, bookRepository);
    }

    @Test
    public void shouldPassBooksToView() {

        // given
        when(bookRepository.getBooksReactively()).thenReturn(Single.just(MANY_BOOKS));

        // when
        presenter.loadBooks();

        // then
        verify(view).displayBooks(MANY_BOOKS);
    }

    @Test
    public void shouldHandleNoBooksFound() {
        // given
        when(bookRepository.getBooksReactively()).thenReturn(Single.<List<Book>>just(EMPTY_LIST));

        //when
        presenter.loadBooks();

        //then
        verify(view).displayNoBooks();
    }

    @Test
    public void shouldHandleError(){
        when(bookRepository.getBooksReactively()).thenReturn(Single.<List<Book>>error(new Throwable()));

        presenter.loadBooks();

        verify(view).displayError();
    }

}


