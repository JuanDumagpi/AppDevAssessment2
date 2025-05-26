package com.example.mynotes

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

//this is the bridge that takes the data and fills it into the UI

class NotesAdapter (private var notes: List<Note>, context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val db: NoteDatabase = NoteDatabase(context)

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //for getting the data from the note database and assigning them to their specific text views
        val titleTextView: TextView = itemView.findViewById(R.id.titleView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentView)
        //for deleting
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteThis)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text = note.noteTitle
        holder.contentTextView.text = note.noteContent

        holder.deleteButton.setOnClickListener {
            db.deleteNotes(note.id)
            refresh(db.getNotes())
            Toast.makeText(holder.itemView.context, "Note's erased!", Toast.LENGTH_SHORT).show()
        }
    }

    fun refresh(newNotes: List<Note>){
        notes=newNotes
        notifyDataSetChanged()
    }
}