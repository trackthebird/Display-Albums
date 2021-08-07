package com.trackthebird.displayalbum.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.trackthebird.displayalbum.R
import com.trackthebird.displayalbum.adapter.AlbumDisplayRecyclerViewAdapter
import com.trackthebird.displayalbum.databinding.AlbumFragmentBinding
import com.trackthebird.displayalbum.databinding.AlbumPhotoDisplayFragmentBinding
import com.trackthebird.displayalbum.viewmodel.AlbumViewModel

class AlbumPhotoDisplayFragment : Fragment() {

    private val TAG : String = "AlbumPhotoDisplayFragment"
    private lateinit var mBinding: AlbumPhotoDisplayFragmentBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.album_photo_display_fragment,
            container,
            false )
        with(mBinding){
            arguments?.let {
                idTextviewAlbumId.text = getString(R.string.album_id_text).format(it.getInt("album_id"))
                idTextviewPhotoId.text = getString(R.string.photos_id_text).format(it.getInt("photo_id"))
                idTextviewAlbumText.text = it.getString("image_text")
                Picasso.get()
                    .load(it.getString("image_url"))
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error_icon)
                    .into(idImageviewAlbumImage)
            }
        }
        return mBinding.root
    }
}