package edu.ewubd.myclassnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class notesAdapter extends BaseAdapter {

    private Context context;
    private ArrayList notes;
    public notesAdapter(Context ct, ArrayList<NoteCLass> notes) {
        this.context = ct;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(this.context).inflate(R.layout.item_view, null);
        TextView noteCourseId = view.findViewById(R.id.course_title);
        TextView noteTopic = view.findViewById(R.id.Heading);
        TextView dateandtime = view.findViewById(R.id.timeoutput);
        TextView description= view.findViewById(R.id.description);
        NoteCLass currentNote = (NoteCLass) notes.get(i);
        noteCourseId.setText(currentNote.courseTitle);
        noteTopic.setText(currentNote.notesTitle);
        dateandtime.setText(currentNote.dateAndTime);
        description.setText(currentNote.description);
        return view;
    }
}