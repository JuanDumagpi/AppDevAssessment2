package com.example.mynotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mynotes.databinding.ActivityAddNotesBinding

class AddNotes : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding

    //the database
    private lateinit var db: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        db = NoteDatabase(this)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //captures the input from the add notes activity edit texts and places them into the database
        //a small popup will show if successful
        binding.saveButton.setOnClickListener{
            val title = binding.noteTitle.text.toString()
            val content = binding.noteContent.text.toString()
            val notes = Note(0, title, content)
            db.insertNote(notes)
            finish()
            Toast.makeText(this, "Note saved successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}