package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mynotes.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    lateinit var  username : EditText
    lateinit var  password : EditText
    lateinit var  loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //calls the splash screen and its duration
        Thread.sleep(3000)
        installSplashScreen()
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.buttonLogin.setOnClickListener(View.OnClickListener {
            if (binding.textUsername.text.toString() == "user" && binding.textPassword.text.toString() == "pass"){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"Login Successful.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Login Failed.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}