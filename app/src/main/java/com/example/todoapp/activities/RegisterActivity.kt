package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityLogInBinding
import com.example.todoapp.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

const val REGISTER_ACTIVITY_TEXT = "com.example.todoapp.activities.message"

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


        binding.loginButton.setOnClickListener{
            onBackPressed()
        }

        val intent: Intent = Intent(this, UserPlansActivity::class.java).apply{
            putExtra(REGISTER_ACTIVITY_TEXT, "Successful Authentication! ")
        }

        binding.registerButton.setOnClickListener {
            Log.d("USER_ACTION", "BUTTON_CLICKED")
            val email = binding.emailInputEditText.text.toString();
            val pass = binding.passwordEditTxet.text.toString();
            val name = binding.nameEditText.text.toString();
            if(email.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Please fill out all required fields", Toast.LENGTH_SHORT ).show()
            }
            else {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                    startActivity(intent)
                }

            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    }
