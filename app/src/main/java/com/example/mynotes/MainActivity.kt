package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mynotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//binding makes it easier for us so we dont have to use findviewbyid
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        This adds functionality to the addMote button which sends us to the AddNotes activity
        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNotes::class.java)
            startActivity(intent)
            Toast.makeText(this, "add button!", Toast.LENGTH_SHORT).show()
        }
    }
}