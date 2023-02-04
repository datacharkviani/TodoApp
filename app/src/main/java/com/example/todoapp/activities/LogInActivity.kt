package com.example.todoapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityLogInBinding
import com.example.todoapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

const val LOGIN_ACTIVITY_TEXT = "com.example.todoapp.activities.message"

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
//        setContentView(binding.root)


        binding.registrationButton.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        val intent: Intent = Intent(this, UserPlansActivity::class.java).apply{
            putExtra(LOGIN_ACTIVITY_TEXT, "login successfully! ")
        }

        binding.logInButton.setOnClickListener {
            Log.d("USER_ACTION", "BUTTON_CLICKED")
            val email = binding.emailInputEditText.text.toString();
            val pass = binding.passwordEditTxet.text.toString();

            if(email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
                        startActivity(intent)
                }
            }else{
                Toast.makeText(this, "please enter a valid account", Toast.LENGTH_SHORT ).show()
            }

        }




    }
}