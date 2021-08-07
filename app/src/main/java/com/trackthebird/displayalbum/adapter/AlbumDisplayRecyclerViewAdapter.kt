package com.trackthebird.displayalbum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trackthebird.displayalbum.R
import com.trackthebird.displayalbum.databinding.AlbumInfoItemBinding
import com.trackthebird.displayalbum.model.Album

class AlbumDisplayRecyclerViewAdapter(val mContext: Context) :
    RecyclerView.Adapter<AlbumDisplayRecyclerViewAdapter.ViewHolder>(){

    private val TAG by lazy {
        "AlbumDisplayRecyclerViewAdapter"
    }
    private var mAlbumsList: List<Album> = arrayListOf()

    /**
     * Function updated RecyclerView List
     */
    fun updateAlbumList(albumList: List<Album>) {
        this.mAlbumsList = albumList
    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {
       val viewBinding: AlbumInfoItemBinding = DataBindingUtil.inflate(
           LayoutInflater.from(parent.context),
           R.layout.album_info_item,
           parent,
           false
       )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder( holder: ViewHolder,  position: Int ) {
        with(holder.bind()) {
            with(mAlbumsList[position]){
                with(mContext) {
                    with(resources) {
                        idTextviewAlbumText.text = title
                        Picasso.get()
                            .load(thumbnailUrl)
                            .resize(96, 112)
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_error_icon)
                            .centerCrop()
                            .into(idImageviewAlbumThumbnail)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if(!mAlbumsList.isNullOrEmpty()) {
            return mAlbumsList.size
        }
        return 0
    }

    /**
     * View Holder class
     */
    class ViewHolder(val viewBinding: AlbumInfoItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind() : AlbumInfoItemBinding {
            return viewBinding
        }
    }


}