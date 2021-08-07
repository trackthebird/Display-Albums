package com.trackthebird.displayalbum.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.trackthebird.displayalbum.adapter.AlbumDisplayRecyclerViewAdapter
import com.trackthebird.displayalbum.databinding.AlbumFragmentBinding
import com.trackthebird.displayalbum.viewmodel.AlbumViewModel

class AlbumFragment : Fragment() {

    private val TAG : String = "AlbumFragment"

    private lateinit var mBinding: AlbumFragmentBinding
    private lateinit var mViewModel: AlbumViewModel
    private lateinit var mAlbumDisplayRecyclerViewAdapter: AlbumDisplayRecyclerViewAdapter
    private var mAlbumId: Int = 1 // Default value

    /**
     * Function initialises variables and functions
     */
    private fun initialise() {
        mAlbumDisplayRecyclerViewAdapter = AlbumDisplayRecyclerViewAdapter(requireActivity())
        with(mBinding) {
            with(idRecyclerViewAlbumInfo){
                apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = mAlbumDisplayRecyclerViewAdapter
                }
            }
            idTextviewAlbumInfo.text = getString(R.string.album_id_text).format(mAlbumId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mAlbumId = it.getInt("album_id")
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.album_fragment,
            container,
            false )
        mBinding.apply {
            viewmodel = mViewModel
            lifecycleOwner = this@AlbumFragment
        }

        initialise()
        setupLiveData()
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mViewModel = ViewModelProvider(requireActivity()).get(AlbumViewModel::class.java)
    }

    /**
     * Function setups live data observer task
     */
    private fun setupLiveData() {
        if (requireContext().isNetworkAvailable()) {
            mBinding.idProgressbar.visibility = View.VISIBLE
            with(mViewModel) {
                getAllAlbums(mAlbumId).observe(viewLifecycleOwner, Observer { data ->
                    if(!data.isNullOrEmpty()){
                        mAlbumDisplayRecyclerViewAdapter.apply {
                            updateAlbumList(data)
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

}