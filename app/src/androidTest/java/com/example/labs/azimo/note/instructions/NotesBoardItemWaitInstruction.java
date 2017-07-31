package com.example.labs.azimo.note.instructions;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.azimolabs.conditionwatcher.Instruction;
import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.config.AzimoTestApplication;
import com.example.labs.azimo.note.ui.activity.NotesActivity;
import com.example.labs.azimo.note.ui.adapter.NotesAdapter;
import com.example.labs.azimo.note.ui.adapter.model.NoteAdapterViewModel;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class NotesBoardItemWaitInstruction extends Instruction {
    @Override
    public String getDescription() {
        return "Item in notes board on position '" +  getDataContainer().getInt(InstructionKeys.KEY_INTEGER) +
                "' should contain text '" + getDataContainer().getString(InstructionKeys.KEY_STRING) + "'.";
    }

    @Override
    public boolean checkCondition() {
        String message = getDataContainer().getString(InstructionKeys.KEY_STRING);
        int expectedPos = getDataContainer().getInt(InstructionKeys.KEY_INTEGER);

        Activity activity = AzimoTestApplication.getInstance().getCurrentActivity();
        if (activity != null && activity instanceof NotesActivity) {
            RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.rvNotes);
            NotesAdapter notesAdapter = (NotesAdapter) recyclerView.getAdapter();

            if (expectedPos < notesAdapter.getItemCount()) {
                NoteAdapterViewModel item = notesAdapter.getItem(expectedPos);
                return item.getNote().getMessage().equals(message);
            }
        }

        return false;
    }
}
