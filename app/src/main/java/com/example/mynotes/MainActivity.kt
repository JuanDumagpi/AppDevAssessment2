package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//binding makes it easier for us so we dont have to use findviewbyid
    private lateinit var binding: ActivityMainBinding
//for the database being added and updated on the main activity view
    private lateinit var db: NoteDatabase
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        setContentView(binding.root)

        db = NoteDatabase(this)
        notesAdapter = NotesAdapter(db.getNotes(), this)

        binding.notesRecycler.layoutManager = LinearLayoutManager(this)
        binding.notesRecycler.adapter = notesAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



//        This adds functionality to the addMote button which sends us to the AddNotes activity
        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNotes::class.java)
            startActivity(intent)
        }
    }
//refreshes the data on the main views regularly
    override fun onResume() {
        super.onResume()
        notesAdapter.refresh(db.getNotes())
    }
}