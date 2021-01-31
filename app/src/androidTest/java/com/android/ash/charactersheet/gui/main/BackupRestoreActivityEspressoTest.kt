package com.android.ash.charactersheet.gui.main

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.withToolbarTitle
import org.hamcrest.Matchers.startsWith
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Test

class BackupRestoreActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<BackupRestoreActivity>

    @After
    fun after() {
        scenario.close()
    }

    @Test
    fun onCreate() {

        // Arrange
        scenario = ActivityScenario.launch(BackupRestoreActivity::class.java)

        // Act
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Assert
        onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(Is.`is`("Backup & Restore"))))
        onView(withId(R.id.backup_restore_backup_to_cloud_button)).check(matches(withText("Backup to Cloud")))
        onView(withId(R.id.backup_restore_backup_directory)).check(matches(withText(startsWith("Backup Directory:"))))
        onView(withId(R.id.backup_restore_backup_dndv35)).check(matches(isNotChecked()))
        onView(withId(R.id.backup_restore_backup_dnd5e)).check(matches(isNotChecked()))
        onView(withId(R.id.backup_restore_backup_pathfinder)).check(matches(isNotChecked()))
        onView(withId(R.id.backup_restore_backup_to_file_button)).check(matches(withText("Backup to File")))
        onView(withId(R.id.backup_restore_restore_directory)).check(matches(withText(startsWith("Restore Directory:"))))
    }

}