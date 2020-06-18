package com.example.pawan.mvpsample.book.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pawan.mvpsample.BaseFragment;

import com.example.pawan.mvpsample.book.adapters.BookListAdapter;
import com.example.pawan.mvpsample.book.presenters.BookListFragmentPresenter;
import com.example.pawan.mvpsample.book.views.BookListFragmentView;
import com.example.pawan.mvpsample.R;
import com.example.pawan.mvpsample.book.impl.DatabaseBookRepository;
import com.example.pawan.mvpsample.book.models.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawan on 27/01/18.
 */

public class BookListFragment extends BaseFragment implements BookListFragmentView {

    private BookListFragmentPresenter presenter;
    private Context mContext;
    private Communicator communicator;
    private List<Book> books;
    private View view;
    private BookListAdapter adapter;

    public static BookListFragment getInstance(Bundle bundle) {
        BookListFragment fragment = new BookListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_book_list, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        presenter = new BookListFragmentPresenter(this, new DatabaseBookRepository(mContext.getApplicationContext()));
        presenter.loadBooks();
    }

    private void initViews() {
        books = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new BookListAdapter(mContext, books);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void displayBooks(List<Book> list) {
        if (books.isEmpty()) {
            books.clear();
        }
        books.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayNoBooks() {
        if (communicator != null) {
            communicator.respond();
        }
    }

    @Override
    public void displayError() {
        Toast.makeText(mContext,"Something went wrong.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setCommunicator(Communicator communicator) {
        this.communicator = communicator;
    }

    public interface Communicator {
        void respond();
    }
}
