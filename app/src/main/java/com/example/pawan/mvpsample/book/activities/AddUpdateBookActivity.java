package com.example.pawan.mvpsample.book.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.pawan.mvpsample.book.fragments.AddBookFragment;
import com.example.pawan.mvpsample.BaseActivity;
import com.example.pawan.mvpsample.R;

/**
 * Created by Pawan on 27/01/18.
 */

public class AddUpdateBookActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        loadFragment();

    }


    private void loadFragment() {
        AddBookFragment fragment = AddBookFragment.getInstance(new Bundle());
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, fragment).commit();
    }


}
