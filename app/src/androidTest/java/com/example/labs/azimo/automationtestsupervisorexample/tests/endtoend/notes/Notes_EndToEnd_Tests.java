package com.example.labs.azimo.automationtestsupervisorexample.tests.endtoend.notes;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.view.View;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMock;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMockResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMockResponseCodes;
import com.example.labs.azimo.automationtestsupervisorexample.config.AzimoTestApplication;
import com.example.labs.azimo.automationtestsupervisorexample.data.TestUsers;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.AddNoteActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.LoginActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.NotesActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.RegisterActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.WelcomeActivity;
import com.example.labs.azimo.automationtestsupervisorexample.utils.TestApiService;

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
import static com.example.labs.azimo.automationtestsupervisorexample.instructions.InstructionUtils.waitForEnteredActivity;
import static com.example.labs.azimo.automationtestsupervisorexample.instructions.InstructionUtils.waitForItemWithTextAppearAtPosOfNotesBoard;
import static com.example.labs.azimo.automationtestsupervisorexample.instructions.InstructionUtils.waitForNotesBoardRefreshingStatusIs;
import static org.hamcrest.core.Is.is;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class Notes_EndToEnd_Tests {

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
    public void testNotes_withFabButtonOnNotesBoard_userShouldBeAbleToAddNewNote() throws Exception {
        final CloudMock cloudMock = AzimoTestApplication.getInstance().getComponent().getCloudMock();
        final TestApiService testApiService = new TestApiService(cloudMock);
        final User user = TestUsers.generateNewUniqueUser();

        final String textToEnter = "test_message";

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
        waitForNotesBoardRefreshingStatusIs(View.GONE);
        onView(withText(textToEnter)).check(doesNotExist());
        onView(withId(R.id.fabAddNote)).perform(click());

        /** {@link AddNoteActivity} */
        waitForEnteredActivity(AddNoteActivity.class);
        onView(withId(R.id.etMessage)).perform(clearText(), typeText(textToEnter), closeSoftKeyboard());
        onView(withId(R.id.btnSave)).perform(click());

        /** {@link NotesActivity} */
        waitForEnteredActivity(NotesActivity.class);
        waitForNotesBoardRefreshingStatusIs(View.GONE);
        waitForItemWithTextAppearAtPosOfNotesBoard(0, textToEnter);
        onView(withText(textToEnter)).check(matches(isDisplayed()));
    }

    @Test
    public void testNotes_whenClickedOnLogoutButton_shouldRemoveUserAndLeaveToWelcomeScreen() throws Exception {
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
