package com.example.kotlinnotesapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.kotlinnotesapp.data.Note
import com.example.kotlinnotesapp.databinding.NoteRowBinding

class NoteAdapter(var list: List<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var myListener: NoteClickListener? = null

    inner class NoteViewHolder(val binding: NoteRowBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = NoteRowBinding.inflate(inflater, parent, false)

        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        var note = list.get(position)

        holder.binding.title.text = note.title
        holder.binding.description.text = note.description

        holder.binding.cardview.setOnClickListener{
            myListener?.onClick(note.id)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addNewData(newList: List<Note>){
        this.list = newList
        calculateDiff(this.list, newList)
    }

    fun calculateDiff(oldList: List<Note>, newList: List<Note>){
        var result = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
            override fun getOldListSize(): Int {
                return oldList.size
            }

            override fun getNewListSize(): Int {
                return newList.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                var oldItem = oldList[oldItemPosition]
                var newItem = newList[newItemPosition]

                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                var oldItem = oldList[oldItemPosition]
                var newItem = newList[newItemPosition]

                return (oldItem.id == newItem.id
                        &&
                        oldItem.title == newItem.title
                        &&
                        oldItem.description == newItem.description)
            }

        })
        result.dispatchUpdatesTo(this)
    }

    fun setOnClickListener(listener: NoteClickListener){
        myListener = listener
    }
}

interface NoteClickListener{
    fun onClick(id: Int)
}