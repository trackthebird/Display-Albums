package com.trackthebird.displayalbum.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.trackthebird.displayalbum.R

class AlbumFragment : Fragment() {

    private val TAG : String = "AlbumFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val button = view.findViewById<Button>(R.id.button_album_details)
//        button?.setOnClickListener{
//            findNavController().navigate(R.id.image_display_fragment, null) // Pass any arguments if any
//        }
    }


}