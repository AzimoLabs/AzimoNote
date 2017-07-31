package com.example.labs.azimo.automationtestsupervisorexample.tests.endtoend.dispatcher;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.labs.azimo.automationtestsupervisorexample.api.manager.UserManager;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMock;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMockResponse;
import com.example.labs.azimo.automationtestsupervisorexample.api.mock.CloudMockResponseCodes;
import com.example.labs.azimo.automationtestsupervisorexample.config.AzimoTestApplication;
import com.example.labs.azimo.automationtestsupervisorexample.data.TestUsers;
import com.example.labs.azimo.automationtestsupervisorexample.data.UserDataStore;
import com.example.labs.azimo.automationtestsupervisorexample.data.model.User;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.DispatcherActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.NotesActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.WelcomeActivity;
import com.example.labs.azimo.automationtestsupervisorexample.utils.TestApiService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static com.example.labs.azimo.automationtestsupervisorexample.instructions.InstructionUtils.waitForEnteredActivity;
import static org.hamcrest.core.Is.is;

/**
 * Created by F1sherKK on 31/07/2017.
 */

public class Dispatcher_EndToEnd_Tests {

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
    public void testDispatcher_whenUserLoggedOut_shouldEnterWelcomeScreen() throws Exception {
        /** {@link WelcomeActivity} */
        waitForEnteredActivity(WelcomeActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof WelcomeActivity, is(true));
    }

    @Test
    public void testDispatcher_whenUserLoggedIn_shouldEnterNotesScreen() throws Exception {
        final CloudMock cloudMock = AzimoTestApplication.getInstance().getComponent().getCloudMock();
        final TestApiService testApiService = new TestApiService(cloudMock);
        final UserDataStore userDataStore = AzimoTestApplication.getInstance().getComponent().getUserDataStore();
        final User user = TestUsers.generateNewUniqueUser();

        CloudMockResponse response = testApiService.registerUser(user.getEmail(), user.getPassword());
        assertThat(response.code == CloudMockResponseCodes.RESPONSE_CODE_200
                || response.code == CloudMockResponseCodes.RESPONSE_CODE_402, is(true));

        userDataStore.saveUser(user);
        AzimoTestApplication.getInstance().getCurrentActivity().startActivity(
                new Intent(AzimoTestApplication.getInstance().getCurrentActivity(), DispatcherActivity.class));

        /** {@link NotesActivity} */
        waitForEnteredActivity(NotesActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof NotesActivity, is(true));
    }
}
