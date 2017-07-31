package com.example.labs.azimo.note.api.manager;


import com.example.labs.azimo.note.api.mock.ClockMockHttpException;
import com.example.labs.azimo.note.api.mock.CloudMockResponseCodes;
import com.example.labs.azimo.note.ui.activity.LoginActivity;
import com.example.labs.azimo.note.ui.activity.RegisterActivity;
import com.example.labs.azimo.note.ui.activity.base.BaseActivity;

/**
 * Created by F1sherKK on 24/07/2017.
 */

public class ApiErrorManager {

    private BaseActivity activity;

    public ApiErrorManager(BaseActivity baseActivity) {
        this.activity = baseActivity;
    }

    public void handleError(Throwable e) {
        if (e instanceof ClockMockHttpException) {
            handleCloudMockHttpException((ClockMockHttpException) e);
        }
    }

    private void handleCloudMockHttpException(ClockMockHttpException e) {
        switch (e.getErrorCode()) {
            case CloudMockResponseCodes.RESPONSE_CODE_401:
                if (activity instanceof LoginActivity) {
                    activity.showMessageDialog(e.getMessage());
                }
                break;

            case CloudMockResponseCodes.RESPONSE_CODE_402:
                if (activity instanceof RegisterActivity) {
                    activity.showMessageDialog(e.getMessage());
                }
                break;

            default:
                activity.showMessageDialog(e.getMessage());
                break;
        }
    }
}
