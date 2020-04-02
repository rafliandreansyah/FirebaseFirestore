package com.azhara.firebasefirestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azhara.firebasefirestore.R
import com.azhara.firebasefirestore.model.DiaryEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_diary.view.*

class DiaryAdapter: ListAdapter<DiaryEntity ,DiaryAdapter.DiaryViewHolder>(DIFFUTIL_CALLBACK) {

    companion object{
        val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<DiaryEntity>(){
            override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryViewHolder = DiaryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_diary, parent, false))


    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiaryViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(diary: DiaryEntity){
            with(itemView){
                item_title_diary.text = diary.title
                item_body_diary.text = diary.text
                Glide.with(itemView)
                    .load(diary.picture)
                    .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error_outline_black_24dp))
                    .into(item_profile_image)
            }
        }
    }
}