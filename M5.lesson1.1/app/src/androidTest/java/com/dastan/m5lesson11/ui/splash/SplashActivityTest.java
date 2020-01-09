package com.dastan.m5lesson11.ui.splash;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.dastan.m5lesson11.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void splashActivityTest() {
        onView(
                allOf(withId(R.id.onBoardImageView),
                        childAtPosition(
                                withParent(withId(R.id.viewPager)),
                                0),
                        isDisplayed())).check(matches(isDisplayed()));

        onView(
                allOf(withId(R.id.textView), withText("Cristiano Ronaldo"),
                        childAtPosition(
                                withParent(withId(R.id.viewPager)),
                                1),
                        isDisplayed())).check(matches(withText("Cristiano Ronaldo")));

        onView(
                allOf(withId(R.id.btnNext),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed())).check(matches(isDisplayed()));

        onView(
                allOf(withId(R.id.btnNext), withText("next"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed())).perform(click());

        onView(
                allOf(withId(R.id.onBoardImageView),
                        childAtPosition(
                                withParent(withId(R.id.viewPager)),
                                0),
                        isDisplayed())).check(matches(isDisplayed()));

        onView(
                allOf(withId(R.id.textView), withText("Dos Santos"),
                        childAtPosition(
                                withParent(withId(R.id.viewPager)),
                                1),
                        isDisplayed())).check(matches(withText("Dos Santos")));

        onView(
                allOf(withId(R.id.btnNext),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed())).check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
