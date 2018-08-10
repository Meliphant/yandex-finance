package ya.co.yandex_finance.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.hamcrest.Matchers.allOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ya.co.yandex_finance.R

@RunWith(AndroidJUnit4::class)
class AddTransactionAndAddedOnRecyclerTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testAddTransaction() {

        val itemsCountBefore = getRecyclerCount()

        onView(withId(R.id.fab_expand_menu_button))
                .perform(click())
        onView(allOf(withId(R.id.fab_new_income), isDisplayed()))
                .perform(click())
        onView(allOf(withId(R.id.tr_amount), isDisplayed()))
                .perform(click())
        onView(allOf(withId(R.id.tr_amount), isDisplayed()))
                .perform(replaceText("500"), closeSoftKeyboard())
        onView(allOf(withId(R.id.name), isDisplayed()))
                .perform(click())
        onView(allOf(withId(R.id.name), isDisplayed()))
                .perform(replaceText("Test"), closeSoftKeyboard())
        onView(allOf(withId(R.id.name), withText("Test"), isDisplayed()))
                .perform(pressImeActionButton())
        onView(allOf(withId(R.id.tv_save), isDisplayed()))
                .perform(click())

        val itemsAdded = getRecyclerCount() - itemsCountBefore

        Assert.assertEquals(1, itemsAdded)
    }

    private fun getRecyclerCount(): Int {
        val recyclerView = mActivityTestRule.activity
                .findViewById(R.id.rv_list_transactions) as RecyclerView

        return recyclerView.adapter.itemCount
    }
}
