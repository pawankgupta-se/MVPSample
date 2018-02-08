package com.example.pawan.mvpsample.book.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.pawan.mvpsample.BaseActivity;
import com.example.pawan.mvpsample.book.fragments.BookListFragment;
import com.example.pawan.mvpsample.book.presenters.BookListFragmentPresenter;
import com.example.pawan.mvpsample.book.fragments.NoBookFragment;
import com.example.pawan.mvpsample.R;
import com.example.pawan.mvpsample.book.models.Book;

public class BookActivity extends BaseActivity implements BookListFragment.Communicator {
    private static final String TAG = Book.class.getSimpleName();
    private BookListFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        loadBookListFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_book:
                startAddBookActivity();
                return true;
            case R.id.help:
                break;
            default:
                break;
        }

        return false;
    }

    private void startAddBookActivity() {
        Intent intent = new Intent(this, AddUpdateBookActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_book_options, menu);
        return true;
    }

    private void loadBookListFragment() {
        BookListFragment fragment = BookListFragment.getInstance(new Bundle());
        fragment.setCommunicator(this);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment).commit();

    }

    private void loadNoBookFragment() {
        NoBookFragment fragment = NoBookFragment.getInstance(new Bundle());
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    public void respond() {
        loadNoBookFragment();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }
}
