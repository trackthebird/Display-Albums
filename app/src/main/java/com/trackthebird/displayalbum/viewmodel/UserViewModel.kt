package com.trackthebird.displayalbum.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trackthebird.displayalbum.model.User
import com.trackthebird.displayalbum.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    /**
     * Initialize all user variable by lazy.
     */
    private val allUsers: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also {
            loadAllUsers()
        }
    }

    /**
     * Function returns list of all users from View Model
     */
    fun getAllUsers() : LiveData<List<User>> {
        return allUsers
    }

    /**
     * Asynchronously downloads user's list from specified URL
     */
    fun loadAllUsers() {
        val networkService : ApiInterface = ApiInterface.create()
        val call: Call<List<User>> = networkService.getAllUsersList()
        with(call){
            enqueue(object: Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    allUsers.postValue(response.body())
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    allUsers.postValue(null)
                }
            })
        }
    }

}