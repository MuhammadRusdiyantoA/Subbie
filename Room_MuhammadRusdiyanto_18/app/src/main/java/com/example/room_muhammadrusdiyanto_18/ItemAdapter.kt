package com.example.room_muhammadrusdiyanto_18

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_subject.view.*
import com.example.room_muhammadrusdiyanto_18.room.Subject

class ItemAdapter(val subjects: ArrayList<Subject>, val listener: (Subject) -> Unit) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val subject = subjects[position]

        holder.view.out_subjTitle.text = subject.subj_name
        holder.view.out_subjTeach.text = subject.subj_teach

        holder.itemView.setOnClickListener{
            listener(subject)
        }
    }

    override fun getItemCount() = subjects.size

    class ItemViewHolder(val view: View) :RecyclerView.ViewHolder(view)

    fun setData(list: List<Subject>){
        subjects.clear()
        subjects.addAll(list)
        notifyDataSetChanged()
    }
}