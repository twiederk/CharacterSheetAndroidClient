package com.android.ash.charactersheet.dac.dao.sql.sqlite

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.mock

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