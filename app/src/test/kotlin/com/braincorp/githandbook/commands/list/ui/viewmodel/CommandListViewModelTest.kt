package com.braincorp.githandbook.commands.list.ui.viewmodel

import app.cash.turbine.test
import com.braincorp.githandbook.commands.list.domain.repository.CommandRepository
import com.braincorp.githandbook.commands.list.ui.mapping.toUi
import com.braincorp.githandbook.commands.testtools.stubCommandList
import com.braincorp.githandbook.commands.testtools.stubUiCommand
import com.braincorp.githandbook.core.log.Logger
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CommandListViewModelTest {

    private val mockRepository = mockk<CommandRepository>()
    private val mockLogger = mockk<Logger>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    private val viewModel = CommandListViewModel(
        mockRepository,
        mockLogger,
        testDispatcher
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `getAllCommands should set correct view states`() = runTest {
        // GIVEN
        val commands = stubCommandList()
        every { mockRepository.getAllCommands() } returns flowOf(commands)

        // WHEN
        viewModel.getAllCommands()

        // THEN
        viewModel.state.test {
            skipItems(count = 1)
            val loading = CommandListUiState().onLoading()
            assertThat(awaitItem()).isEqualTo(loading)
            val commandsReceived = loading.onCommandsReceived(commands.map { it.toUi() })
            assertThat(awaitItem()).isEqualTo(commandsReceived)
            val finishedLoading = commandsReceived.onFinishedLoading()
            assertThat(awaitItem()).isEqualTo(finishedLoading)
        }
    }

    @Test
    fun `getAllCommands should log error`() = runTest {
        // GIVEN
        val exception = IOException()
        every { mockRepository.getAllCommands() } returns flow { throw exception }

        // WHEN
        viewModel.getAllCommands()

        // THEN
        advanceUntilIdle()
        verify { mockLogger.error(message = "Error getting commands", exception) }
    }

    @Test
    fun `onCommandClicked should send ShowCommandDetails action`() = runTest {
        // GIVEN
        val command = stubUiCommand()

        // WHEN
        viewModel.onCommandClicked(command)

        // THEN
        viewModel.action.test {
            val expected = CommandListUiAction.ShowCommandDetails(command)
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    @Test
    fun `onReferenceMenuItemClicked should send ShowReference action`() = runTest {
        // WHEN
        viewModel.onReferenceMenuItemClicked()

        // THEN
        viewModel.action.test {
            val expected = CommandListUiAction.ShowReference
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    @Test
    fun `onPrivacyPolicyMenuItemClicked should send ShowPrivacyPolicy action`() = runTest {
        // WHEN
        viewModel.onPrivacyPolicyMenuItemClicked()

        // THEN
        viewModel.action.test {
            val expected = CommandListUiAction.ShowPrivacyPolicy
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    @Test
    fun `onAppInfoMenuItemClicked should send ShowAppInfo action`() = runTest {
        // WHEN
        viewModel.onAppInfoMenuItemClicked()

        // THEN
        viewModel.action.test {
            val expected = CommandListUiAction.ShowAppInfo
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    @Test
    fun `onPrivacySettingsMenuItemClicked should send ShowPrivacySettings action`() = runTest {
        // WHEN
        viewModel.onPrivacySettingsMenuItemClicked()

        // THEN
        viewModel.action.test {
            val expected = CommandListUiAction.ShowPrivacySettings
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    @Test
    fun `onReferenceDialogueButtonClicked should send ViewWebPage action`() = runTest {
        // WHEN
        viewModel.onReferenceDialogueButtonClicked()

        // THEN
        viewModel.action.test {
            val expected = CommandListUiAction.ViewWebPage(
                url = "https://git-scm.com/documentation"
            )
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }
}
