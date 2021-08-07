package com.trackthebird.displayalbum.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trackthebird.displayalbum.R
import com.trackthebird.displayalbum.adapter.UserDisplayRecyclerViewAdapter
import com.trackthebird.displayalbum.databinding.UserFragmentBinding
import com.trackthebird.displayalbum.viewmodel.UserViewModel

class UserFragment : Fragment() {

    private val TAG : String = "UserFragment"

    private lateinit var mBinding: UserFragmentBinding
    private lateinit var mViewModel: UserViewModel
    private lateinit var mUserDisplayRecyclerViewAdapter: UserDisplayRecyclerViewAdapter

    /**
     * Function initialises variables and functions
     */
    private fun initialise() {
        mUserDisplayRecyclerViewAdapter = UserDisplayRecyclerViewAdapter(requireActivity())
        with(mBinding) {
            mUserDisplayRecyclerViewAdapter = UserDisplayRecyclerViewAdapter(requireActivity())
            with(idRecyclerViewUserInfo){
                apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = mUserDisplayRecyclerViewAdapter
                }
            }
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_fragment,
            container,
        false )
        mBinding.apply {
          viewmodel = mViewModel
          lifecycleOwner = this@UserFragment
        }
        initialise()
        setupLiveData()
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
    }

    /**
     * Function setups live data observer task
     */
    private fun setupLiveData() {
        with(mViewModel) {
            getAllUsers().observe(viewLifecycleOwner, Observer { data ->
                if(!data.isNullOrEmpty()){
                    Log.d(TAG, "Data received...Display data ${data}")
                    mUserDisplayRecyclerViewAdapter.apply {
                        updateUserList(data)
                        notifyDataSetChanged()
                    }
                }
            })
        }
    }
}