package com.azhara.firebasefirestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.azhara.firebasefirestore.R
import com.azhara.firebasefirestore.model.DiaryEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.items_diary.view.*

class DiaryAdapter: RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    private val listItem = ArrayList<DiaryEntity>()

    fun setData(data: ArrayList<DiaryEntity>){
        listItem.clear()
        listItem.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryViewHolder = DiaryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items_diary, parent, false))

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.bind(listItem[position])
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