package com.trackthebird.displayalbum.network

import com.trackthebird.displayalbum.model.Album
import com.trackthebird.displayalbum.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users")
    fun getAllUsersList() : Call<List<User>>
    // For  https://jsonplaceholder.typicode.com/users

    @GET("photos/albumId={albumId}")
    fun getAlbumList(@Path("albumId") albumId: Int) : Call<List<Album>>
    // For https://jsonplaceholder.typicode.com/photos?albumId=3

    companion object {

        val BASE_URL = "https://jsonplaceholder.typicode.com/"

        // Creates Retrofit instance.
        fun create() : ApiInterface {
            val retrofit = Retrofit.Builder()
                .run {
                    baseUrl(BASE_URL)
                        .run { addConverterFactory(GsonConverterFactory.create()) }
                        .run { build() }
                }
            return retrofit.create(ApiInterface::class.java)
        }
    }
}