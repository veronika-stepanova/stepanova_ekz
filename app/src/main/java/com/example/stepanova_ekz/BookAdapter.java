package com.example.stepanova_ekz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private LayoutInflater inflater;
    private int layout;

    public BookAdapter(Context context, int resource, List<Book> books) {
        super(context, resource, books);
        inflater = LayoutInflater.from(context);
        layout = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(layout, parent, false);

        TextView titleView = view.findViewById(R.id.book_title);
        TextView authorView = view.findViewById(R.id.book_author);
        TextView yearView = view.findViewById(R.id.book_year);

        Book book = getItem(position);

        titleView.setText(book.getTitle());
        authorView.setText(book.getAuthor());
        yearView.setText(String.format("Release Year: %s", book.getYear()));

        return view;
    }
}
