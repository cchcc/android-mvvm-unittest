package cchcc.learn.amu.e04

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.*

class E04ViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val viewModel = E04ViewModel()

    // given
    private val given_name = "abc"
    private val given_phone = "111"
    private val expected_value = "Name : $given_name\nPhone : $given_phone"


    private val nameAndPhoneObserver = Observer<Unit> {
        viewModel.nameAndPhone.value = given_name to given_phone
    }

    private val contactObserver = mockk<Observer<String>>()

    @Before
    fun observe() {
        viewModel.pickContactAction.observeForever(nameAndPhoneObserver)
        viewModel.contact.observeForever(contactObserver)
    }

    @Test
    fun pickContact() {
        // when
        every { contactObserver.onChanged(expected_value) } returns Unit
        viewModel.pickContact()

        // then
        verify { contactObserver.onChanged(expected_value) }
        Assert.assertEquals(expected_value, viewModel.contact.value)
    }

    @After
    fun removeObserver() {
        viewModel.contact.removeObserver(contactObserver)
        viewModel.pickContactAction.removeObserver(nameAndPhoneObserver)
    }
}