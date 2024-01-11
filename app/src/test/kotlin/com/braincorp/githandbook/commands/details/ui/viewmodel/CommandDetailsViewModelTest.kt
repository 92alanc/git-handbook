package com.braincorp.githandbook.commands.details.ui.viewmodel

import app.cash.turbine.test
import com.braincorp.githandbook.commands.testtools.stubUiCommand
import com.braincorp.githandbook.commands.testtools.stubUiParameter
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CommandDetailsViewModelTest {

    private val viewModel = CommandDetailsViewModel()

    @Test
    fun `start should set correct view states`() = runTest {
        // GIVEN
        val command = stubUiCommand()

        // WHEN
        viewModel.start(command)

        // THEN
        viewModel.state.test {
            val expected = CommandDetailsUiState()
                .onCommandReceived(command)
                .onParameterChanged(command.parameters.first())
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }

    @Test
    fun `onParameterItemClicked should set correct view state`() = runTest {
        // GIVEN
        val parameter = stubUiParameter()

        // WHEN
        viewModel.onParameterItemClicked(parameter)

        // THEN
        viewModel.state.test {
            val expected = CommandDetailsUiState().onParameterChanged(parameter)
            assertThat(awaitItem()).isEqualTo(expected)
        }
    }
}
