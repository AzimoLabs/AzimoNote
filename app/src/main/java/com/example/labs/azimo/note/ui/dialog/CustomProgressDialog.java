package com.example.labs.azimo.note.ui.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import com.example.labs.azimo.note.R;

/**
 * Created by F1sherKK on 25/07/2017.
 */

public class CustomProgressDialog extends DialogFragment {

    public static final String TAG = "CustomProgressDialog";
    private static final String ARG_MESSAGE = "ARG_MESSAGE";

    private String message;

    public static CustomProgressDialog newInstance(String message) {
        CustomProgressDialog dialog = new CustomProgressDialog();
        final Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString(ARG_MESSAGE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog progressDialog;
        if (Build.VERSION.SDK_INT >= 21) {
            progressDialog = new android.app.ProgressDialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog);
        } else {
            progressDialog = new android.app.ProgressDialog(getActivity());
        }
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(android.app.ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        return progressDialog;
    }
}