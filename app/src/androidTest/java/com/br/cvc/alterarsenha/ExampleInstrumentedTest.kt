package com.br.cvc.alterarsenha

import android.app.Activity
import android.app.Instrumentation
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.br.cvc.alterarsenha.activity.PasswordActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import android.support.test.espresso.UiController
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.PreferenceMatchers.isEnabled
import android.view.View
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import android.app.Instrumentation.ActivityResult
import android.support.test.InstrumentationRegistry.getTargetContext
import com.br.cvc.alterarsenha.activity.SucessActivity
import org.junit.Assert
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.InstrumentationRegistry.getInstrumentation






/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    class ButtonSubimitTest
    {
        @Rule
        @JvmField val activity = ActivityTestRule<PasswordActivity>(PasswordActivity::class.java)

        @Test @Throws(Exception::class)
        fun testeClick()
        {
            // Create an ActivityMonitor that monitor ChildActivity, do not interrupt, do not return mock result:
            val activityMonitor = getInstrumentation().addMonitor(SucessActivity::class.java!!.name, null, false)

            val password1 = "123445"
            val password2 = "123445"
            onView(withId(R.id.password1)). perform(typeText(password1))
            onView(withId(R.id.password2)). perform(scrollTo(), typeText(password2))
            onView(withId(R.id.btn_submit)).perform(scrollTo(), click())

            val childActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5) as? SucessActivity
            assertNotNull(childActivity)

        }
    }

}
