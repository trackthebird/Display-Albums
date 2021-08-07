package com.trackthebird.displayalbum.network

import com.trackthebird.displayalbum.model.Album
import com.trackthebird.displayalbum.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("users")
    fun getAllUsersList(): Call<List<User>>

    @GET("photos")
    fun getAlbumList(@Query("albumId") albumId: Int): Call<List<Album>>

    companion object {

        val BASE_URL = "https://jsonplaceholder.typicode.com/"

        // Creates Retrofit instance.
        fun create(): ApiInterface {
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