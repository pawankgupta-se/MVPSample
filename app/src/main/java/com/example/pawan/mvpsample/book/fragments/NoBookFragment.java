package com.example.pawan.mvpsample.book.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pawan.mvpsample.BaseFragment;
import com.example.pawan.mvpsample.R;
import com.example.pawan.mvpsample.book.activities.AddUpdateBookActivity;

/**
 * Created by Pawan on 27/01/18.
 */

public class NoBookFragment extends BaseFragment {
    private View view;
    private  Button addBookBtn;
    private Context mContext;

    public static NoBookFragment getInstance(Bundle bundle) {
        NoBookFragment fragment = new NoBookFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frgment_no_book, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();




    }

    private void initViews(){
        addBookBtn = (Button) view.findViewById(R.id.add_book_btn);
        addBookBtn.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, AddUpdateBookActivity.class);
            mContext.startActivity(intent);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
}
