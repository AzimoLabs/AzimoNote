package com.example.labs.azimo.note.instructions;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.azimolabs.conditionwatcher.Instruction;
import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.config.AzimoTestApplication;
import com.example.labs.azimo.note.ui.activity.NotesActivity;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class NotesBoardLoadingInstruction extends Instruction {
    @Override
    public String getDescription() {
        return "Notes board is loading is: " + getDataContainer().getBoolean(InstructionKeys.KEY_BOOLEAN);
    }

    @Override
    public boolean checkCondition() {
        boolean loadingStatus = getDataContainer().getBoolean(InstructionKeys.KEY_BOOLEAN);

        Activity activity = AzimoTestApplication.getInstance().getCurrentActivity();
        if (activity != null && activity instanceof NotesActivity) {
            SwipeRefreshLayout loader = (SwipeRefreshLayout) activity.findViewById(R.id.srlLoading);
            return loader.isRefreshing() == loadingStatus;
        }

        return false;
    }
}
