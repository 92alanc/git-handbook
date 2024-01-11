package com.braincorp.githandbook.commands.list.data.mapping

import android.content.Context
import com.braincorp.githandbook.commands.list.data.model.DbCommand
import com.braincorp.githandbook.commands.list.domain.model.Command
import com.braincorp.githandbook.commands.list.domain.model.Parameter
import com.braincorp.githandbook.commands.testtools.stubDbCommand
import com.braincorp.githandbook.commands.testtools.stubDbDescriptionList
import com.braincorp.githandbook.commands.testtools.stubDbParameterList
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import org.junit.Test

class DataToDomainMapperImplTest {

    private val mockContext = mockk<Context>()
    private val mapper = DataToDomainMapperImpl(mockContext)

    @Test
    fun `map should convert data objects map to domain list`() {
        // GIVEN
        val dbCommand = stubDbCommand()
        val parametersAndDescriptions = stubDbParameterList().zip(stubDbDescriptionList()).toMap()
        val map = mapOf(dbCommand to parametersAndDescriptions)

        // WHEN
        val actual = mapper.map(map)

        // THEN
        val expected = getExpectedCommandList(dbCommand)
        assertThat(actual).isEqualTo(expected)
    }

    private fun getExpectedCommandList(dbCommand: DbCommand) = listOf(
        Command(
            id = dbCommand.id,
            name = dbCommand.name,
            parameters = listOf(
                Parameter(
                    name = null,
                    description = "",
                    example = ""
                ),
                Parameter(
                    name = null,
                    description = "",
                    example = ""
                )
            )
        )
    )
}
