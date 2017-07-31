package com.example.labs.azimo.note.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.data.model.Note;
import com.example.labs.azimo.note.ui.adapter.model.NoteAdapterViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by F1sherKK on 27/07/2017.
 */

public class NoteViewHolder extends BaseViewHolder<NoteAdapterViewModel> {

    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.tvDate)
    TextView tvDate;

    private NoteAdapterViewModel noteViewModel;

    public NoteViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(NoteAdapterViewModel item) {
        noteViewModel = item;
        Note note = noteViewModel.getNote();

        tvMessage.setText(note.getMessage());
        tvDate.setText(convertTime(note.getCreationDate()));
    }

    private String convertTime(long timeInMillis) {
        DateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss", Locale.US);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return  formatter.format(calendar.getTime());
    }
}
