package ru.iteco.fmhandroid.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.R

@LargeTest
@RunWith(AllureAndroidJUnit4::class)
class NotActiveStatusTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun notActiveStatusTest() {
        val materialTextView = onView(
            allOf(
                withId(R.id.all_news_text_view), withText("All news"),
                childAtPosition(
                    allOf(
                        withId(R.id.container_list_news_include_on_fragment_main),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val materialTextView2 = onView(
            allOf(
                withId(R.id.all_news_text_view), withText("All news"),
                childAtPosition(
                    allOf(
                        withId(R.id.container_list_news_include_on_fragment_main),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialTextView2.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.edit_news_material_button),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_list_news_include),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val appCompatImageView = onView(
            allOf(
                withId(R.id.edit_news_item_image_view),
                withContentDescription("News editing button"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.news_item_material_card_view),
                        0
                    ),
                    15
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val switchMaterial = onView(
            allOf(
                withId(R.id.switcher), withText("Active"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0
                    ),
                    5
                )
            )
        )
        switchMaterial.perform(scrollTo(), click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0
                    ),
                    6
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val textView = onView(
            allOf(
                withId(R.id.news_item_published_text_view), withText("NOT ACTIVE"),
                withParent(withParent(withId(R.id.news_item_material_card_view))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("NOT ACTIVE")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
