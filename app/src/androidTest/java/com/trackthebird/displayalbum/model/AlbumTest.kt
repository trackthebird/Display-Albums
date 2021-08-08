package com.trackthebird.displayalbum.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class AlbumTest {

    private lateinit var album: Album

    @Before
    fun setUp() {
        album = Album(
            1,
            1,
            "https://via.placeholder.com/150/92c952",
            "Title",
            "https://via.placeholder.com"
        )
    }

    @Test
    fun getAlbumId() {
        assertTrue(album.albumId == 1)
    }

    @Test
    fun getId() {
        assertTrue(album.id == 1)
    }

    @Test
    fun getThumbnailUrl() {
        assertTrue(album.thumbnailUrl == "https://via.placeholder.com/150/92c952")
    }

    @Test
    fun getTitle() {
        assertTrue(album.title == "Title")
    }

    @Test
    fun getUrl() {
        assertTrue(album.url == "https://via.placeholder.com")
    }

    @Test
    fun testInvalidAlbumId() {
        assertFalse(album.albumId == 2)
    }

    @Test
    fun testInvalidId() {
        assertFalse(album.id == 2)
    }

    @Test
    fun testInvalidThumbnailUrl() {
        assertFalse(album.thumbnailUrl == "")
    }

    @Test
    fun testInvalidTitle() {
        assertFalse(album.title == "")
    }

    @Test
    fun testInvalidUrl() {
        assertFalse(album.url == "")
    }

}