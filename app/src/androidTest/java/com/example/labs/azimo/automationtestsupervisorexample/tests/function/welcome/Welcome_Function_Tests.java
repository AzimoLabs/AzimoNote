package com.example.labs.azimo.automationtestsupervisorexample.tests.function.welcome;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.labs.azimo.automationtestsupervisorexample.R;
import com.example.labs.azimo.automationtestsupervisorexample.config.AzimoTestApplication;
import com.example.labs.azimo.automationtestsupervisorexample.instructions.InstructionUtils;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.LoginActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.RegisterActivity;
import com.example.labs.azimo.automationtestsupervisorexample.ui.activity.WelcomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.labs.azimo.automationtestsupervisorexample.instructions.InstructionUtils.waitForEnteredActivity;
import static org.hamcrest.core.Is.is;

/**
 * Created by F1sherKK on 28/07/2017.
 */

public class Welcome_Function_Tests {

    @Rule
    public ActivityTestRule<WelcomeActivity> activityRule =
            new ActivityTestRule<>(WelcomeActivity.class, true, false);

    @Before
    public void before() {
        activityRule.launchActivity(new Intent());
    }

    @After
    public void after() {
        Activity currentActivity = AzimoTestApplication.getInstance().getCurrentActivity();

        Intent returnToStartingActivityIntent = new Intent(currentActivity, WelcomeActivity.class);
        returnToStartingActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        currentActivity.startActivity(returnToStartingActivityIntent);
    }

    @Test
    public void testWelcome_whenClickedLoginButton_shouldEnterLoginScreen() throws Exception {
        /** {@link WelcomeActivity} */
        onView(withId(R.id.btnLogin)).perform(click());

        /** {@link LoginActivity} */
        waitForEnteredActivity(LoginActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof  LoginActivity, is(true));
    }

    @Test
    public void testWelcome_whenClickedRegisterButton_shouldEnterLoginScreen() throws Exception {
        /** {@link WelcomeActivity} */
        onView(withId(R.id.btnRegister)).perform(click());

        /** {@link RegisterActivity} */
        waitForEnteredActivity(RegisterActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof  RegisterActivity, is(true));
    }
}
