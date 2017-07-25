package com.example.labs.azimo.automationtestsupervisorexample.ui.activity.base;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.ui.dialog.CustomProgressDialog;
import com.example.labs.azimo.automationtestsupervisorexample.ui.dialog.MessageDialog;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void showMessageDialog(String message) {
        MessageDialog dialog = MessageDialog.newInstance(message);
        dialog.show(getFragmentManager(), MessageDialog.TAG);
    }

    public void showLoadingDialog() {
        String message = getString(R.string.progress_dialog_message);
        CustomProgressDialog dialog = CustomProgressDialog.newInstance(message);
        dialog.show(getFragmentManager(), CustomProgressDialog.TAG);

    }

    public void hideLoadingDialog() {
        dismissDialogByTag(CustomProgressDialog.TAG);
    }

    private void dismissDialogByTag(String tag) {
        DialogFragment progressDialogFragment = (DialogFragment) getFragmentManager().findFragmentByTag(tag);
        if (progressDialogFragment != null) {
            progressDialogFragment.dismissAllowingStateLoss();
        }
    }

}
