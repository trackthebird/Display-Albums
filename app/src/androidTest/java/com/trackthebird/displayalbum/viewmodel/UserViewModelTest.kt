package com.trackthebird.displayalbum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trackthebird.displayalbum.model.Album
import com.trackthebird.displayalbum.model.User
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class UserViewModelTest {


    private lateinit var mVm: UserViewModel

    @Before
    fun setUp() {
        mVm = UserViewModel()
        mVm.loadAllUsers()
    }

    @Test
    fun getAllUsers() {
        val data = mVm.getAllUsers()
        assertTrue(data != null)
    }

    @Test
    fun loadAllUsers() {
        val data = mVm.loadAllUsers()
        assertTrue(data == Unit)
    }
}