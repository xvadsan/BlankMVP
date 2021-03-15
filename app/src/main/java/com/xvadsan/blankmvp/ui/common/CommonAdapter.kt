package com.xvadsan.blankmvp.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.xvadsan.blankmvp.R
import com.xvadsan.blankmvp.data.image.GlideApp
import com.xvadsan.blankmvp.domain.models.ProfileModel
import kotlinx.android.synthetic.main.item_common.view.*

class CommonAdapter(private var listener: Listener) : RecyclerView.Adapter<CommonAdapter.CommonViewHolder>() {

    private val data = ArrayList<ProfileModel>()

    interface Listener {
        fun onClick(model: ProfileModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_common, parent, false)
        return CommonViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: CommonViewHolder, position: Int) {
        viewHolder.bind(data[position])
        viewHolder.itemView.cvRoot.setOnClickListener { listener.onClick(data[position]) }
    }

    fun setItems(items: List<ProfileModel>) {
        this.data.clear()
        this.data.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class CommonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: ProfileModel) {
            setChatAvatar(model.photo, itemView)
            itemView.tvLogin.text = model.login
        }

        private fun setChatAvatar(image: ByteArray?, view: View) {
            GlideApp.with(view.context)
                .asBitmap()
                .load(image)
                .apply { RequestOptions().circleCrop() }
                .placeholder(R.drawable.ic_preview_empty)
                .circleCrop()
                .into(view.ivPhoto)
        }
    }
}