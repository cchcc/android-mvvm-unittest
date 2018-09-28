package cchcc.learn.amu.e04

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import io.mockk.mockk
import org.junit.*

class E04ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Test
    fun pickContact() {
        val viewModel = E04ViewModel()
        val lifecycle = LifecycleRegistry(mockk()).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        // given
        val givenName = "abc"
        val givenPhone = "111"

        viewModel.contact.observe({ lifecycle }) {}
        viewModel.pickContactAction.observe({ lifecycle }) {
            viewModel.nameAndPhone.value = givenName to givenPhone
        }

        // when
        viewModel.pickContact()

        // then
        Assert.assertEquals("Name : $givenName\nPhone : $givenPhone", viewModel.contact.value)
    }
}