package com.trackthebird.displayalbum.`interface`

interface OnItemClickListener {
    fun onClick(id: Int)
    fun onClick(albumId: Int, photoId: Int, imageUrl: String, imageText: String)
}