package com.example.labs.azimo.note.ui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.example.labs.azimo.note.R;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class MessageDialog extends DialogFragment {
    public static final String TAG = "MessageDialog";

    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";

    private String title;
    private String message;

    public static MessageDialog newInstance(String message) {
        MessageDialog dialog = new MessageDialog();
        final Bundle args = new Bundle();
        args.putInt(ARG_TITLE, 0);
        args.putString(ARG_MESSAGE, message);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(ARG_TITLE);
        message = getArguments().getString(ARG_MESSAGE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity(), R.style.Theme_AppCompat_Light_Dialog_Alert);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        return builder.setPositiveButton(R.string.message_dialog_confirm, null).create();
    }
}
