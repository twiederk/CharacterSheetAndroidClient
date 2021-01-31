package com.android.ash.charactersheet.dac.dao.sql.sqlite

import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ClasspathScriptResourceTest {

    @Test
    fun load() {
        // arrange
        val underTest = ClasspathScriptResource("/sql/create_database.sql")

        // act
        val inputStream = underTest.load(mock())

        // assert
        assertThat(inputStream).isNotNull
    }

}