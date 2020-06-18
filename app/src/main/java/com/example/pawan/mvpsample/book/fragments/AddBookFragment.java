package com.example.pawan.mvpsample.book.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pawan.mvpsample.BaseFragment;
import com.example.pawan.mvpsample.R;
import com.example.pawan.mvpsample.book.models.Book;
import com.example.pawan.mvpsample.helpers.DatabaseHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by Pawan on 27/01/18.
 */

public class AddBookFragment extends BaseFragment {
    private View view;
    private EditText title, author;
    private Button save;
    private Context mContext;

    public static AddBookFragment getInstance(Bundle bundle) {
        AddBookFragment fragment = new AddBookFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_book, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        title = view.findViewById(R.id.title);
        author = view.findViewById(R.id.author);
        save = view.findViewById(R.id.save_btn);

        save.setOnClickListener(view -> saveBook());

    }

    private void saveBook() {
        if (validateInput()) {
            Book book = new Book();
            book.setTitle(title.getText().toString());
            book.setAuthor(author.getText().toString());
            DatabaseHelper helper = new DatabaseHelper(mContext.getApplicationContext());
            Dao<Book, Integer> bookDao = null;
            try {
                bookDao = helper.getBookDao();
                bookDao.create(book);
                ((Activity) mContext).finish();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(title.getText())) {
            title.setError("Please enter title");
            return false;
        } else if (title.getText().length() < 3) {
            title.setError("Title should contain minimum 3 characters.");
            return false;
        } else if (author.getText().length() > 0 && author.getText().length() < 3) {
            title.setError("Author name should contain minimum 3 characters.");
            return false;
        } else {

            return true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
}
