package com.trackthebird.displayalbum.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.trackthebird.displayalbum.R
import com.trackthebird.displayalbum.`interface`.OnItemClickListener
import com.trackthebird.displayalbum.databinding.UserInfoItemBinding
import com.trackthebird.displayalbum.model.User
import com.trackthebird.displayalbum.utils.Helper.getFormattedString

class UserDisplayRecyclerViewAdapter(
    val mContext: Context,
    val onItemClick: OnItemClickListener
) :
    RecyclerView.Adapter<UserDisplayRecyclerViewAdapter.ViewHolder>() {

    private val TAG by lazy {
        "UserDisplayRecyclerViewAdapter"
    }
    private var mUsersList: List<User> = arrayListOf()

    /**
     * Function updated RecyclerView List
     */
    fun updateUserList(userList: List<User>) {
        this.mUsersList = userList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding: UserInfoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_info_item,
            parent,
            false
        )
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.bind()) {
            with(mUsersList[position]) {
                with(mContext) {
                    with(resources) {
                        idTextviewCellTitle.text =
                            getString(R.string.id).getFormattedString("${id}")
                        idTextviewName.text = getString(R.string.name).getFormattedString(name)
                        idTextviewEmail.text = getString(R.string.email).getFormattedString(email)
                        idTextviewPhone.text = getString(R.string.phone).getFormattedString(phone)
                    }
                }
                idCardview.setOnClickListener { view ->
                    onItemClick.onClick(id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        if (!mUsersList.isNullOrEmpty()) {
            return mUsersList.size
        }
        return 0
    }

    /**
     * View Holder class
     */
    class ViewHolder(val viewBinding: UserInfoItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(): UserInfoItemBinding {
            return viewBinding
        }
    }
}