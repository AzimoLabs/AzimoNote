package com.example.labs.azimo.note.tests.function.login;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.AppCompatTextView;

import com.example.labs.azimo.note.R;
import com.example.labs.azimo.note.config.AzimoTestApplication;
import com.example.labs.azimo.note.data.TestUsers;
import com.example.labs.azimo.note.ui.activity.LoginActivity;
import com.example.labs.azimo.note.ui.activity.RegisterActivity;
import com.example.labs.azimo.note.ui.activity.WelcomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.labs.azimo.note.instructions.InstructionUtils.waitForEnteredActivity;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by F1sherKK on 28/07/2017.
 */

public class Login_Function_Tests {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class, true, false);

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
    public void testLogin_insertWrongEmail_shouldDisplayError() {
        final String incorrectEmail = "incorrect!@#$email";

        /** {@link LoginActivity} */
        onView(withId(R.id.etEmail)).perform(clearText(), typeText(incorrectEmail), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        onView(allOf(isAssignableFrom(AppCompatTextView.class), isDescendantOfA(withId(R.id.tilEmail))))
                .check(matches(withText(R.string.login_error_incorrect_email)));
    }

    @Test
    public void testLogin_whenEmailFieldIsEmpty_shouldDisplayError() {
        /** {@link LoginActivity} */
        onView(withId(R.id.etEmail)).perform(clearText());
        onView(withId(R.id.btnLogin)).perform(click());

        onView(allOf(isAssignableFrom(AppCompatTextView.class), isDescendantOfA(withId(R.id.tilEmail))))
                .check(matches(withText(R.string.login_error_empty_email)));
    }


    @Test
    public void testLogin_whenPasswordFieldIsEmpty_shouldDisplayError() {
        final String correctEmail = TestUsers.LOGIN_TEST_USER_NAME;

        /** {@link LoginActivity} */
        onView(withId(R.id.etEmail)).perform(clearText(), typeText(correctEmail), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        onView(allOf(isAssignableFrom(AppCompatTextView.class), isDescendantOfA(withId(R.id.tilPassword))))
                .check(matches(withText(R.string.login_error_empty_password)));
    }

    @Test
    public void testLogin_whenSwitchingFromLoginToRegister_shouldKeepInsertedData() throws Exception {
        final String login = "test@login.com";
        final String password = "test";

        /** {@link LoginActivity} */
        onView(withId(R.id.etEmail)).perform(clearText(), typeText(login), closeSoftKeyboard());
        onView(withId(R.id.etPassword)).perform(clearText(), typeText(password), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());

        /** {@link RegisterActivity} */
        waitForEnteredActivity(RegisterActivity.class);
        assertThat(AzimoTestApplication.getInstance().getCurrentActivity() instanceof  RegisterActivity, is(true));
        onView(withId(R.id.etEmail)).check(matches(withText(login)));
        onView(withId(R.id.etPassword)).check(matches(withText(password)));
    }
}
