package com.braincorp.githandbook.commands.list.data.repository

import app.cash.turbine.test
import com.braincorp.githandbook.commands.list.data.database.CommandDao
import com.braincorp.githandbook.commands.list.data.mapping.DataToDomainMapper
import com.braincorp.githandbook.commands.testtools.stubCommandList
import com.braincorp.githandbook.commands.testtools.stubDbCommandList
import com.braincorp.githandbook.commands.testtools.stubDbDescription
import com.braincorp.githandbook.commands.testtools.stubDbParameterList
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class CommandRepositoryImplTest {

    private val mockMapper = mockk<DataToDomainMapper>()
    private val mockDatabase = mockk<CommandDao>()
    private val repository = CommandRepositoryImpl(mockMapper, mockDatabase)

    @Test
    fun `getAllCommands should return commands from database`() = runTest {
        // GIVEN
        coEvery { mockDatabase.getAllCommands() } returns stubDbCommandList()
        coEvery {
            mockDatabase.getParametersByCommandId(commandId = any())
        } returns stubDbParameterList()
        coEvery { mockDatabase.getDescriptionById(id = any()) } returns stubDbDescription()

        val expected = stubCommandList()
        every { mockMapper.map(map = any()) } returns expected

        // WHEN
        val flow = repository.getAllCommands()

        // THEN
        flow.test {
            assertThat(awaitItem()).isEqualTo(expected)
            awaitComplete()
        }
    }

    @Test
    fun `getAllCommands should return error`() = runTest {
        // GIVEN
        val expected = IOException()
        coEvery { mockDatabase.getAllCommands() } throws expected

        // WHEN
        val flow = repository.getAllCommands()

        // THEN
        flow.test {
            assertThat(awaitError()).isEqualTo(expected)
        }
    }
}
