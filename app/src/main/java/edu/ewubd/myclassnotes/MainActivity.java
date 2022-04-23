package edu.ewubd.myclassnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView notesListView;
    private TinyDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesListView = findViewById(R.id.notesListview);
        Button exitButton = findViewById(R.id.Exit);
        Button createNewNote = findViewById(R.id.Add);

        db = new TinyDB(MainActivity.this);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        createNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateNote.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        ArrayList objects = db.getListObject("note", NoteCLass.class);
        ArrayList<NoteCLass> note = new ArrayList<NoteCLass>();

        for (Object obj : objects) {
            note.add((NoteCLass)obj);
        }

        notesAdapter adapter = new notesAdapter(MainActivity.this, note);
        this.notesListView.setAdapter(adapter);

        this.notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Intent i = new Intent(MainActivity.this, CreateNote.class);
                i.putExtra("id", index);
                startActivity(i);
            }
        });
    }
}