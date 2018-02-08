package com.example.pawan.mvpsample;

import com.example.pawan.mvpsample.book.models.Book;
import com.example.pawan.mvpsample.book.presenters.BookListFragmentPresenter;
import com.example.pawan.mvpsample.book.views.BookListFragmentView;
import com.example.pawan.mvpsample.repositories.BookRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Pawan on 26/01/18.
 */

public class BookListFragmentPresenterTestWithMockito {
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
        when(bookRepository.getBooks()).thenReturn(MANY_BOOKS);

        // when
        presenter.loadBooks();

        // then
        verify(view).displayBooks(MANY_BOOKS);
    }

    @Test
    public void shouldHandleNoBooksFound() {
        // given
        when(bookRepository.getBooks()).thenReturn(EMPTY_LIST);

        //when
        presenter.loadBooks();

        //then
        verify(view).displayNoBooks();
    }

    @Test
    public void shouldHandleError(){
        when(bookRepository.getBooks()).thenThrow(new RuntimeException("Something went wrong."));

        presenter.loadBooks();

        verify(view).displayError();
    }

}


