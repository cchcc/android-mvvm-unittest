package cchcc.learn.amu.e04

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleRegistry
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