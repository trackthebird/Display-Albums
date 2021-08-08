package com.trackthebird.displayalbum.viewmodel

import androidx.lifecycle.MutableLiveData
import com.trackthebird.displayalbum.model.Album
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AlbumViewModelTest {

    private lateinit var mVm: AlbumViewModel

    @Before
    fun setUp() {
        mVm = AlbumViewModel()
        mVm.allAlbums = MutableLiveData<List<Album>>()
    }

    @Test
    fun getAllAlbums() {
        val albums = mVm.getAllAlbums(1)
        assertTrue(albums != null)
    }

    @Test
    fun loadAlbums() {
        val albums = mVm.loadAlbums(1)
        assertTrue(albums == Unit)
    }
}