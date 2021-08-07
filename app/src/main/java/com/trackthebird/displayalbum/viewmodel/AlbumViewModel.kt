package com.trackthebird.displayalbum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trackthebird.displayalbum.model.Album
import com.trackthebird.displayalbum.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel : ViewModel() {

    /**
     * Initialize allalbums variable by lazy.
     */
    private var mAlbumId = -1
    private lateinit var allAlbums: MutableLiveData<List<Album>>

    /**
     * Function returns list of all albums from View Model
     */
    fun getAllAlbums(albumId: Int): LiveData<List<Album>> {
        if(mAlbumId == albumId) {
            return allAlbums
        }
        allAlbums = MutableLiveData<List<Album>>()
        loadAlbums(albumId)
        mAlbumId = albumId
        return allAlbums
    }

    /**
     * Asynchronously downloads Album's list from specified URL
     */
    fun loadAlbums(albumId: Int) {
        val networkService: ApiInterface = ApiInterface.create()
        val call: Call<List<Album>> = networkService.getAlbumList(albumId)
        with(call) {
            enqueue(object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    allAlbums.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    allAlbums.postValue(null)
                }
            })
        }
    }
}