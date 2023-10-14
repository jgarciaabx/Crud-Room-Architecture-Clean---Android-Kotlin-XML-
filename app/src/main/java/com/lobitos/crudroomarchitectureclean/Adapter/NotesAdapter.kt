package com.lobitos.crudroomarchitectureclean.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lobitos.crudroomarchitectureclean.Models.Note
import com.lobitos.crudroomarchitectureclean.R
import kotlin.random.Random

class NotesAdapter (private val context: Context, val listener: NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NotesAdapter.NoteViewHolder {
       return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        val currenNote = NotesList[position]
        holder.title.text = currenNote.title
        holder.title.isSelected = true
        holder.Note_tv.text = currenNote.note
        holder.date.text = currenNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(context.resources.getColor(randomColor() , null))

        holder.notes_layout.setOnClickListener {
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }

        holder.notes_layout.setOnLongClickListener {
            listener.onLongItemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true
        }

    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    fun updateList(newList: List<Note>)
    {
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }


    fun filterList(search: String){
        NotesList.clear()
//        NotesList.addAll(fullList.filter { it.title.contains(search, true) })
        for (item in fullList){
            if (item.title.contains(search, true) || item.note.contains(search, true)){
                NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun randomColor():Int{
        val list = ArrayList<Int>()
        list.add(R.color.randomColor1)
        list.add(R.color.randomColor2)
        list.add(R.color.randomColor3)
        list.add(R.color.randomColor4)
        list.add(R.color.randomColor5)
        list.add(R.color.randomColor6)
        list.add(R.color.randomColor7)
        list.add(R.color.randomColor8)
        list.add(R.color.randomColor9)
        list.add(R.color.randomColor10)
        list.add(R.color.randomColor11)
        list.add(R.color.randomColor12)
        list.add(R.color.randomColor13)
        list.add(R.color.randomColor14)
        list.add(R.color.randomColor15)
        list.add(R.color.randomColor16)
        list.add(R.color.randomColor17)
        list.add(R.color.randomColor18)
        list.add(R.color.randomColor19)
        list.add(R.color.randomColor20)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }


    inner class NoteViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val Note_tv = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    interface NotesClickListener{
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }

}