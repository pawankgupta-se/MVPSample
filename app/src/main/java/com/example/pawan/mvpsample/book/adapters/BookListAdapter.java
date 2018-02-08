package com.example.pawan.mvpsample.book.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pawan.mvpsample.R;
import com.example.pawan.mvpsample.book.models.Book;

import java.util.List;

/**
 * Created by Pawan on 27/01/18.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private Context mContext;
    private List<Book> books;

    public BookListAdapter(Context mContext, List<Book> books) {
        this.mContext = mContext;
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_element, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(books.get(position).getTitle());
        holder.author.setText(books.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
        }
    }
}
