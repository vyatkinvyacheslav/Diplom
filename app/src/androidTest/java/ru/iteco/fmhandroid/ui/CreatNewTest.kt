package ru.iteco.fmhandroid.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.R

@LargeTest
@RunWith(AllureAndroidJUnit4::class)
class CreatNewTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun creatNewTest() {
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

        val materialButton2 = onView(
            allOf(
                withId(R.id.add_news_image_view), withContentDescription("Add news button"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val checkableImageButton = onView(
            allOf(
                withId(com.google.android.material.R.id.text_input_end_icon),
                withContentDescription("Show dropdown menu"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        checkableImageButton.perform(click())

        val PopupBackgroundView = ""
        val materialTextView2 = onData(anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(`is`("android.widget.PopupWindow$PopupBackgroundView")),
                    0
                )
            )
            .atPosition(7)
        materialTextView2.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.news_item_publish_date_text_input_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.news_item_create_date_text_input_layout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton3.perform(scrollTo(), click())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.news_item_publish_time_text_input_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.news_item_publish_time_text_input_layout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton4.perform(scrollTo(), click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.news_item_description_text_input_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.news_item_description_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("Help"), closeSoftKeyboard())

        val materialButton5 = onView(
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
        materialButton5.perform(scrollTo(), click())

        val textView = onView(
            allOf(
                withId(R.id.news_item_title_text_view), withText("Нужна помощь"),
                withParent(withParent(withId(R.id.news_item_material_card_view))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Нужна помощь")))
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
