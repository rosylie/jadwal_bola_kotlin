package mwsp.lily.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import mwsp.lily.splashscreen.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener{
            var fullname = binding.etFullname.text.toString()
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

            if (fullname.isEmpty())
            {
                binding.etFullname.error = "Name required!"
                binding.etFullname.requestFocus()
            }
            else if (email.isEmpty())
            {
                binding.etEmail.error = "Email required!"
                binding.etEmail.requestFocus()
            }
            else if (password.isEmpty())
            {
                binding.etPassword.error = "Password required!"
                binding.etPassword.requestFocus()
            }
            else
            {
                //register
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful)
                    {
                        Toast.makeText(this,"User Registered Successfully", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"User Registered Failed due to ${it.exception}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}