package com.android.ash.charactersheet.gui.admin

import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.core.Is
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AdministrationMenuActivityInstrumentationTest {

    @get:Rule
    val activityRule = ActivityTestRule(AdministrationMenuActivity::class.java)

    @Test
    fun onCreate() {
        // Assert
        onView(withId(R.id.administration_menu_race_button)).check(matches(withText("Races")))
        onView(withId(R.id.administration_menu_character_class_button)).check(matches(withText("Character Classes")))
        onView(withId(R.id.administration_menu_ability_button)).check(matches(withText("Abilities")))
        onView(withId(R.id.administration_menu_skill_button)).check(matches(withText("Skills")))
        onView(withId(R.id.administration_menu_feat_button)).check(matches(withText("Feats")))
        onView(withId(R.id.administration_menu_weapon_button)).check(matches(withText("Weapons")))
        onView(withId(R.id.administration_menu_armor_button)).check(matches(withText("Armor")))
        onView(withId(R.id.administration_menu_good_button)).check(matches(withText("Goods")))
        onView(withId(R.id.administration_menu_spelllist_button)).check(matches(withText("Spell Lists")))
        onView(withId(R.id.administration_menu_spell_button)).check(matches(withText("Spells")))

        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Administration"))))
    }
}