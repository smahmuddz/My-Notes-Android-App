package edu.ewubd.myclassnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateNote extends AppCompatActivity {
    private TinyDB db;
    private ArrayList<NoteCLass> note;
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_note);

        db = new TinyDB(CreateNote.this);
        ArrayList objects = db.getListObject("note", NoteCLass.class);
        ArrayList<NoteCLass> note = new ArrayList<NoteCLass>();

        for (Object obj : objects) {
            note.add((NoteCLass) obj);
        }

        Button backButton = findViewById(R.id.backAddnotes);
        Button deleteButton=findViewById(R.id.Delete);
        Button saveButton = findViewById(R.id.savenote);
        EditText courseTitleInput = findViewById(R.id.addcourseTitle);
        EditText topicInput = findViewById(R.id.addnotestitle);
        EditText descInput = findViewById(R.id.adddescription);

        int id = getIntent().getIntExtra("id", -1);
        System.out.println("Sent ID:" + id);
        if (id != -1) {
            this.index = id;
            NoteCLass currentNote = note.get(index);
            courseTitleInput.setText(currentNote.courseTitle);
            topicInput.setText(currentNote.notesTitle);
            descInput.setText(currentNote.description);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseTitle = courseTitleInput.getText().toString().trim();
                String topic = topicInput.getText().toString().trim();
                String desc = descInput.getText().toString().trim();

                if (courseTitle.length() == 0 || desc.length()==0) {
                    Toast.makeText(CreateNote.this, "Titles or Description Cannot Be Empty\nPlease Fill up all of the fields.", Toast.LENGTH_LONG).show();
                    return; }
               NoteCLass newNote = new NoteCLass(courseTitle, topic, desc);

                if (index != -1) {
                    note.set(index, newNote);
                } else {
                    note.add(newNote);
                }

                ArrayList objects = new ArrayList<Object>();

                for (NoteCLass note : note) {
                    objects.add((Object) note);
                }

                db.putListObject("note", objects);

                Toast.makeText(CreateNote.this, "Note Saved", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseTitle = courseTitleInput.getText().toString().trim();
                String topic = topicInput.getText().toString().trim();
                String desc = descInput.getText().toString().trim();

                if (courseTitle.length() == 0 && topic.length() == 0 && desc.length()==0) {
                    Toast.makeText(CreateNote.this, "Cannot Delete note Before Creating", Toast.LENGTH_LONG).show();
                    return;
                }
                else if(courseTitle.length() == 0 || topic.length() == 0 || desc.length()==0){
                    Toast.makeText(CreateNote.this, "Please Fill up all of the fields", Toast.LENGTH_LONG).show();
                    return;
                }
                objects.remove(index);
                db.putListObject("note", objects);
                Toast.makeText(CreateNote.this, "Note Deleted Successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}