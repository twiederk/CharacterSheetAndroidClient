package com.android.ash.charactersheet.dac.dao.sql.sqlite

import android.content.res.Resources
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class RawScriptResourceTest {

    @Test
    fun load() {

        // arrange
        val underTest = RawScriptResource(R.raw.create_database)
        val resources: Resources = mock()
        whenever(resources.openRawResource(R.raw.create_database)).thenReturn("hallo".byteInputStream())

        // act
        val inputStream = underTest.load(resources)

        // assert
        assertThat(inputStream).isNotNull
    }

}