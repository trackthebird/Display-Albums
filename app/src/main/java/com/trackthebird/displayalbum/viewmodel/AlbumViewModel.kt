package com.trackthebird.displayalbum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trackthebird.displayalbum.model.Album
import com.trackthebird.displayalbum.model.User
import com.trackthebird.displayalbum.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel : ViewModel() {

    /**
     * Initialize allalbums variable by lazy.
     */
    private val albumId : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    private val allAlbums : MutableLiveData<List<Album>> by lazy {
        MutableLiveData<List<Album>>().also {
            loadAlbums(albumId.value ?: 0)
        }
    }

    /**
     * Function returns list of all albums from View Model
     */
    fun getAllAlbums(id: Int) : LiveData<List<Album>> {
        albumId.value = id
        return allAlbums
    }

    /**
     * Asynchronously downloads Album's list from specified URL
     */
    fun loadAlbums(albumId: Int) {
        val networkService : ApiInterface = ApiInterface.create()
        val call: Call<List<Album>> = networkService.getAlbumList(albumId)
        with(call){
            enqueue(object: Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    allAlbums.postValue(response.body())
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    allAlbums.postValue(null)
                }
            })
        }
    }
// https://jsonplaceholder.typicode.com/photos/albumId=3 - NW
// https://jsonplaceholder.typicode.com/photos?albumId=3
}