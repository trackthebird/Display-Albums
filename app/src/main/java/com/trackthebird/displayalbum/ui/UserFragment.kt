package com.trackthebird.displayalbum.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.trackthebird.displayalbum.R
import com.trackthebird.displayalbum.utils.Helper.isNetworkAvailable
import com.trackthebird.displayalbum.`interface`.OnItemClickListener
import com.trackthebird.displayalbum.adapter.UserDisplayRecyclerViewAdapter
import com.trackthebird.displayalbum.databinding.UserFragmentBinding
import com.trackthebird.displayalbum.viewmodel.UserViewModel
import androidx.navigation.fragment.findNavController
import java.util.*

class UserFragment : Fragment(), OnItemClickListener {

    private val TAG : String = "UserFragment"

    private lateinit var mBinding: UserFragmentBinding
    private lateinit var mViewModel: UserViewModel
    private lateinit var mUserDisplayRecyclerViewAdapter: UserDisplayRecyclerViewAdapter

    /**
     * Function initialises variables and functions
     */
    private fun initialise() {
        mUserDisplayRecyclerViewAdapter = UserDisplayRecyclerViewAdapter(requireActivity(), this)
        with(mBinding) {
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
        if (requireContext().isNetworkAvailable()) {
            mBinding.idProgressbar.visibility = View.VISIBLE
            with(mViewModel) {
                getAllUsers().observe(viewLifecycleOwner, Observer { data ->
                    if(!data.isNullOrEmpty()){
                        mUserDisplayRecyclerViewAdapter.apply {
                            updateUserList(data)
                            notifyDataSetChanged()
                        }
                    }
                    // Stop Progress bar
                    Handler(Looper.getMainLooper()).postDelayed({
                        mBinding.idProgressbar.visibility = View.GONE
                    }, 1000)
                })
            }
        }
        else{
            Toast.makeText(requireActivity(), resources.getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show()
        }

    }

    override fun onClick(id: Int) {
        Log.d(TAG, "Item Clicked ${id}")
        with(Bundle()) {
            putInt("album_id", id)
            findNavController().navigate(R.id.album_details_fragment, this)
        }
    }
}


