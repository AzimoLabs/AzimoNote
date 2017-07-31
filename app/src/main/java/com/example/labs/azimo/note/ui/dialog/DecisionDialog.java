package com.example.labs.azimo.note.ui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.ui.activity.NotesActivity;

/**
 * Created by F1sherKK on 28/07/2017.
 */

public class DecisionDialog extends DialogFragment {
    public static final String TAG = "DecisionDialog";

    public static final String ARG_MESSAGE = "MessageKey";
    public static final String ARG_CONFIRM_BUTTON = "ConfirmButtonKey";
    public static final String ARG_CANCEL_BUTTON = "CancelButtonKey";

    private int messageId;
    private int okButtonTextId;
    private int cancelButtonTextId;

    private DialogInterface.OnClickListener onOkClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dismiss();
            NotesActivity rootView = (NotesActivity) getActivity();
            rootView.onLogoutConfirmed();
        }
    };

    private DialogInterface.OnClickListener onCancelClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dismiss();
        }
    };

    public static DecisionDialog newInstance(int messageId, int okButtonId, int cancelButtonId) {
        DecisionDialog decisionDialog = new DecisionDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_MESSAGE, messageId);
        args.putInt(ARG_CONFIRM_BUTTON, okButtonId);
        args.putInt(ARG_CANCEL_BUTTON, cancelButtonId);
        decisionDialog.setArguments(args);
        return decisionDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrieveData();
    }

    private void retrieveData() {
        messageId = getArguments().getInt(ARG_MESSAGE);
        okButtonTextId = getArguments().getInt(ARG_CONFIRM_BUTTON);
        cancelButtonTextId = getArguments().getInt(ARG_CANCEL_BUTTON);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity(), R.style.Theme_AppCompat_Light_Dialog);

        builder.setMessage(messageId);
        builder.setPositiveButton(okButtonTextId, onOkClickListener);
        builder.setNegativeButton(cancelButtonTextId, onCancelClickListener);

        return builder.create();
    }
}
