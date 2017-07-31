package com.example.labs.azimo.note.tests.endtoend.login;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.api.manager.UserManager;
import com.example.labs.azimo.note.api.mock.CloudMock;
import com.example.labs.azimo.note.api.mock.CloudMockResponse;
import com.example.labs.azimo.note.api.mock.CloudMockResponseCodes;
import com.example.labs.azimo.note.config.AzimoTestApplication;
import com.example.labs.azimo.note.data.TestUsers;
import com.example.labs.azimo.note.data.model.User;
import com.example.labs.azimo.note.ui.activity.DispatcherActivity;
import com.example.labs.azimo.note.ui.activity.LoginActivity;
import com.example.labs.azimo.note.ui.activity.NotesActivity;
import com.example.labs.azimo.note.ui.activity.WelcomeActivity;
import com.example.labs.azimo.note.utils.TestApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.labs.azimo.note.instructions.InstructionUtils.waitForEnteredActivity;
import static org.hamcrest.core.Is.is;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class Login_EndToEnd_Tests {

    @Rule
    public ActivityTestRule<DispatcherActivity> activityRule =
            new ActivityTestRule<>(DispatcherActivity.class, true, false);

    @Before
    public void before() {
        activityRule.launchActivity(new Intent());
    }

    @After
    public void after() {
        // clean app data after test
        final UserManager userManager = AzimoTestApplication.getInstance().getComponent().getUserManager();
        userManager.logoutCurrentUser();

        // leave to starting activity
        Activity currentActivity = AzimoTestApplication.getInstance().getCurrentActivity();

        Intent returnToStartingActivityIntent = new Intent(currentActivity, WelcomeActivity.class);
        returnToStartingActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        currentActivity.startActivity(returnToStartingActivityIntent);
    }

    @Test
    public void testLogin_whenInsertedCorrectData_shouldBeAbleToLogin() throws Exception {
        final CloudMock cloudMock = AzimoTestApplication.getInstance().getComponent().getCloudMock();
        final TestApiService testApiService = new TestApiService(cloudMock);
        final User user = TestUsers.generateNewUniqueUser();

        CloudMockResponse response = testApiService.registerUser(user.getEmail(), user.getPassword());
        assertThat(response.code == CloudMockResponseCodes.RESPONSE_CODE_200
                || response.code == CloudMockResponseCodes.RESPONSE_CODE_402, is(true));

        /** {@link WelcomeActivity} */
        onView(withId(R.id.btnLogin)).perform(click());

        /** {@link LoginActivity} */
        waitForEnteredActivity(LoginActivity.class);
        onView(withId(R.id.etEmail)).perform(clearText(), typeText(user.getEmail()), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(clearText(), typeText(user.getPassword()), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        /** {@link NotesActivity} */
        waitForEnteredActivity(NotesActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof NotesActivity, is(true));
    }
}
