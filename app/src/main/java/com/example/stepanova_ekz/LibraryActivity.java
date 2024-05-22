package com.example.stepanova_ekz;

import static com.example.stepanova_ekz.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryActivity extends AppCompatActivity {

    private ListView bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_library);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle arguments = getIntent().getExtras();
        String title = arguments != null ? arguments.getString("title") : null;
        String author = arguments != null ? arguments.getString("author") : null;
        Integer year = arguments != null ? arguments.getInt("year") : null;

        if (title != null && author != null && year != null) {
            Book book = new Book(title, author, year);
            MainActivity.Books.add(book);
        }

        bookList = findViewById(id.book_list);
        BookAdapter bookAdapter = new BookAdapter(this, R.layout.book, MainActivity.Books);
        bookList.setAdapter(bookAdapter);

        AdapterView.OnItemClickListener itemListener = (parent, view, position, id) -> {
            Book selected = (Book) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),
                            String.format("%s - %s, %s",selected.getTitle(), selected.getAuthor(), selected.getYear()),
                            Toast.LENGTH_SHORT)
                            .show();
        };
        bookList.setOnItemClickListener(itemListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_menu) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.library) {
            Intent intent = new Intent(this, LibraryActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    public void sort(View view) {
        ArrayList<Book> books = MainActivity.Books;
        books.sort(Comparator.comparing(Book::getTitle));

        BookAdapter bookAdapter = new BookAdapter(this, R.layout.book, books);
        bookList.setAdapter(bookAdapter);
    }

    public void find(View view) {
        EditText searchEditText = findViewById(R.id.searchEditText);

        String searchText = searchEditText.getText().toString().toLowerCase();
        ArrayList<Book> books = new ArrayList<Book>();
        for (Book book: MainActivity.Books) {
            if (book.getTitle().toLowerCase().contains(searchText) || book.getAuthor().toLowerCase().contains(searchText) || String.valueOf(book.getYear()).contains(searchText)) {
                books.add(book);
            }
        }

        BookAdapter bookAdapter = new BookAdapter(this, R.layout.book, books);
        bookList.setAdapter(bookAdapter);
    }
}