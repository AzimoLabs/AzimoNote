package com.example.labs.azimo.note.tests.endtoend.logout;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.api.manager.UserManager;
import com.example.labs.azimo.note.api.mock.CloudMock;
import com.example.labs.azimo.note.api.mock.CloudMockResponse;
import com.example.labs.azimo.note.api.mock.CloudMockResponseCodes;
import com.example.labs.azimo.note.config.AzimoTestApplication;
import com.example.labs.azimo.note.data.TestUsers;
import com.example.labs.azimo.note.data.model.User;
import com.example.labs.azimo.note.ui.activity.AddNoteActivity;
import com.example.labs.azimo.note.ui.activity.LoginActivity;
import com.example.labs.azimo.note.ui.activity.NotesActivity;
import com.example.labs.azimo.note.ui.activity.RegisterActivity;
import com.example.labs.azimo.note.ui.activity.WelcomeActivity;
import com.example.labs.azimo.note.utils.TestApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.labs.azimo.note.instructions.InstructionUtils.waitForEnteredActivity;
import static com.example.labs.azimo.note.instructions.InstructionUtils.waitForItemWithTextAppearAtPosOfNotesBoard;
import static com.example.labs.azimo.note.instructions.InstructionUtils.waitForNotesBoardRefreshingStatusIs;
import static org.hamcrest.core.Is.is;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class Logout_EndToEnd_Tests {

    @Rule
    public ActivityTestRule<WelcomeActivity> activityRule =
            new ActivityTestRule<>(WelcomeActivity.class, true, false);

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
    public void testLogout_whenClickedOnLogoutButton_shouldRemoveUserAndLeaveToWelcomeScreen() throws Exception {
        final User user = TestUsers.generateNewUniqueUser();

        /** {@link WelcomeActivity} */
        onView(withId(R.id.btnRegister)).perform(click());

        /** {@link RegisterActivity} */
        waitForEnteredActivity(RegisterActivity.class);
        onView(withId(R.id.etEmail)).perform(clearText(), typeText(user.getEmail()), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(clearText(), typeText(user.getPassword()), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());

        /** {@link NotesActivity} */
        waitForEnteredActivity(NotesActivity.class);
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());

        // without this sleep, test will be flaky
        // you can't get reference to toolbar menu item in easy way
        // animation of showing toolbar menu takes no longer than 300ms
        Thread.sleep(1500);

        onView(withText(R.string.notes_menu_button_logout)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        /** {@link WelcomeActivity} */
        waitForEnteredActivity(WelcomeActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof WelcomeActivity, is(true));
    }
}
